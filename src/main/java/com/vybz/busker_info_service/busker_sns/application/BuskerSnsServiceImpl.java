package com.vybz.busker_info_service.busker_sns.application;

import com.vybz.busker_info_service.busker_sns.domain.BuskerSns;
import com.vybz.busker_info_service.busker_sns.dto.request.RequestAddBuskerSnsDto;
import com.vybz.busker_info_service.busker_sns.dto.request.RequestDeleteBuskerSnsDto;
import com.vybz.busker_info_service.busker_sns.dto.request.RequestUpdateBuskerSnsDto;
import com.vybz.busker_info_service.busker_sns.dto.response.ResponseBuskerSnsDto;
import com.vybz.busker_info_service.busker_sns.infrastructure.BuskerSnsRepository;
import com.vybz.busker_info_service.common.entity.BaseResponseStatus;
import com.vybz.busker_info_service.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuskerSnsServiceImpl implements BuskerSnsService {

    private final BuskerSnsRepository buskerSnsRepository;

    /**
     * 버스커 SNS 생성
     * @param requestAddBuskerSnsDto
     */
    @Transactional
    @Override
    public void createBuskerSns(RequestAddBuskerSnsDto requestAddBuskerSnsDto) {
        if(buskerSnsRepository.existsByBuskerUuidAndSnsUrlAndDeletedFalse(requestAddBuskerSnsDto.getBuskerUuid(), requestAddBuskerSnsDto.getSnsUrl())) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_BUSKER_SNS);
        }
        buskerSnsRepository.save(requestAddBuskerSnsDto.toEntity());
    }

    /**
     * 버스커 UUID로 버스커 SNS 조회
     * @param buskerUuid
     */
    @Override
    public List<ResponseBuskerSnsDto> findBuskerSnsByBuskerUuid(String buskerUuid) {
        return buskerSnsRepository.findAllByBuskerUuidAndDeletedFalse(buskerUuid)
                .stream()
                .map(ResponseBuskerSnsDto::from)
                .toList();
    }

    /**
     * 버스커 sns 수정
     * @param requestUpdateBuskerSnsDto
     */
    @Transactional
    @Override
    public void updateBuskerSns(RequestUpdateBuskerSnsDto requestUpdateBuskerSnsDto) {
        if(buskerSnsRepository.existsByBuskerUuidAndSnsUrlAndDeletedFalse(requestUpdateBuskerSnsDto.getBuskerUuid(), requestUpdateBuskerSnsDto.getNewSnsUrl())) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_BUSKER_SNS);
        }
        BuskerSns buskerSns = buskerSnsRepository.findByBuskerUuidAndSnsUrlAndDeletedFalse(requestUpdateBuskerSnsDto.getBuskerUuid(), requestUpdateBuskerSnsDto.getOldSnsUrl())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER_SNS));
        buskerSns.updateSnsUrl(requestUpdateBuskerSnsDto.getNewSnsUrl());
    }

    /**
     * 버스커 sns 삭제
     * @param requestDeleteBuskerSnsDto
     */
    @Transactional
    @Override
    public void deleteBuskerSns(RequestDeleteBuskerSnsDto requestDeleteBuskerSnsDto) {
        BuskerSns buskerSns = buskerSnsRepository.findByBuskerUuidAndSnsUrlAndDeletedFalse(requestDeleteBuskerSnsDto.getBuskerUuid(), requestDeleteBuskerSnsDto.getSnsUrl())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER_SNS));
        buskerSns.softDelete();
    }

}
