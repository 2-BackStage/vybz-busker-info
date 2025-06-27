package com.vybz.busker_info_service.busker_info.application;

import com.vybz.busker_info_service.busker_info.domain.BuskerInfo;
import com.vybz.busker_info_service.busker_info.dto.request.BuskerSummary;
import com.vybz.busker_info_service.busker_info.dto.request.RequestAddBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.request.RequestDeleteBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.request.RequestUpdateBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.response.ResponseBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.response.ResponseBuskerProfileDto;
import com.vybz.busker_info_service.busker_info.infrastructure.BuskerInfoRepository;
import com.vybz.busker_info_service.common.entity.BaseResponseStatus;
import com.vybz.busker_info_service.common.exception.BaseException;
import com.vybz.busker_info_service.common.util.ChosungUtils;
import com.vybz.busker_info_service.kafka.event.BuskerSearchUpdateEvent;
import com.vybz.busker_info_service.kafka.producer.BuskerSearchUpdateEventProducer;
import com.vybz.busker_info_service.kafka.producer.DeleteBuskerInfoEventProducer;
import com.vybz.busker_info_service.kafka.producer.UpdateBuskerInfoEventProducer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BuskerInfoServiceImpl implements BuskerInfoService {

    private final BuskerInfoRepository buskerInfoRepository;
    private final UpdateBuskerInfoEventProducer updateBuskerInfoEventProducer;
    private final DeleteBuskerInfoEventProducer deleteBuskerInfoEventProducer;
    private final BuskerSearchUpdateEventProducer buskerSearchUpdateEventProducer;

    /**
     * 버스커 정보 추가
     *
     * @param requestAddBuskerInfoDto
     */
    @Transactional
    @Override
    public void createBuskerInfo(RequestAddBuskerInfoDto requestAddBuskerInfoDto) {
        if (buskerInfoRepository.existsByBuskerUuid(requestAddBuskerInfoDto.getBuskerUuid())) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_BUSKER);
        }
        buskerInfoRepository.save(requestAddBuskerInfoDto.toEntity());
    }

    /**
     * buskerUuid 버스커 정보 조회
     *
     * @param buskerUuid
     */
    @Override
    public ResponseBuskerInfoDto getBuskerInfoByBuskerUuid(String buskerUuid) {
        BuskerInfo buskerInfo = buskerInfoRepository.findByBuskerUuidAndDeletedFalse(buskerUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER));
        return ResponseBuskerInfoDto.from(buskerInfo);
    }

    /**
     * 모든 버스커 정보 조회
     */
    @Override
    public List<ResponseBuskerInfoDto>  getAllBuskerInfo() {
        return buskerInfoRepository.findAllByDeletedFalse()
                .stream()
                .map(ResponseBuskerInfoDto::from)
                .toList();
    }

    /**
     * 버스커 정보 수정
     *
     * @param requestUpdateBuskerInfoDto
     */
    @Transactional
    @Override
    public void updateBuskerInfo(RequestUpdateBuskerInfoDto requestUpdateBuskerInfoDto) {
        BuskerInfo buskerInfo = buskerInfoRepository.findByBuskerUuidAndDeletedFalse(requestUpdateBuskerInfoDto.getBuskerUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER));
        buskerInfoRepository.save(requestUpdateBuskerInfoDto.updateEntity(buskerInfo));

        updateBuskerInfoEventProducer.sendBuskerInfoEvent(RequestUpdateBuskerInfoDto.toBuskerInfoEvent(buskerInfo));
        buskerSearchUpdateEventProducer.send(
                BuskerSearchUpdateEvent.builder()
                        .buskerUuid(buskerInfo.getBuskerUuid())
                        .nickname(buskerInfo.getNickname())
                        .nicknameChosung(ChosungUtils.toChosung(buskerInfo.getNickname())) // ✅ 수정
                        .profileImageUrl(buskerInfo.getProfileImageUrl())
                        .build()
        );


    }

    /**
     * 버스커 정보 삭제
     *
     * @param requestDeleteBuskerInfoDto
     */
    @Transactional
    @Override
    public void deleteBuskerInfo(RequestDeleteBuskerInfoDto requestDeleteBuskerInfoDto) {
        BuskerInfo buskerInfo = buskerInfoRepository.findByBuskerUuidAndDeletedFalse(requestDeleteBuskerInfoDto.getBuskerUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER));
        buskerInfo.softDelete();

        deleteBuskerInfoEventProducer.sendBuskerInfoEvent(requestDeleteBuskerInfoDto.getBuskerUuid());
    }

    /**
     * 버스커 UUID로 버스커 요약 정보 조회
     * @param buskerUuid
     */
    @Override
    public Map<String, BuskerSummary> getUserSummaryBulk(List<String> buskerUuid) {
        List<BuskerInfo> buskerInfo = buskerInfoRepository.findByBuskerUuidIn(buskerUuid);
        return buskerInfo.stream()
                .collect(Collectors.toMap(
                        BuskerInfo::getBuskerUuid,
                        busker -> new BuskerSummary(
                                busker.getBuskerUuid(),
                                busker.getNickname(),
                                busker.getProfileImageUrl()
                        )
                ));
    }

    /**
     * 버스커 프로필 이미지, 닉네임 조회
     *
     * @param buskerUuid
     * @return
     */
    @Override
    public ResponseBuskerProfileDto getBuskerProfileByUuid(String buskerUuid) {

        BuskerInfo busker = buskerInfoRepository.findByBuskerUuidAndDeletedFalse(buskerUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER));

        return ResponseBuskerProfileDto.builder()
                .nickname(busker.getNickname())
                .profileImageUrl(busker.getProfileImageUrl())
                .build();
    }
}
