package com.vybz.busker_info_service.busker_sns.application;

import com.vybz.busker_info_service.busker_sns.domain.BuskerSns;
import com.vybz.busker_info_service.busker_sns.dto.request.RequestDeleteBuskerSnsDto;
import com.vybz.busker_info_service.busker_sns.dto.request.RequestUpsertBuskerSnsDto;
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
     * 버스커 SNS 생성/ 수정
     * @param requestUpsertBuskerSnsDto
     */
    @Transactional
    @Override
    public void upsertBuskerSns(RequestUpsertBuskerSnsDto requestUpsertBuskerSnsDto) {
        String oldSnsUrl = requestUpsertBuskerSnsDto.getOldSnsUrl();

        if (buskerSnsRepository.existsByBuskerUuidAndSnsUrlAndDeletedFalse(requestUpsertBuskerSnsDto.getBuskerUuid(), requestUpsertBuskerSnsDto.getSnsUrl())) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_BUSKER_SNS);
        }

        if (oldSnsUrl == null || oldSnsUrl.isBlank()) {
            buskerSnsRepository.save(requestUpsertBuskerSnsDto.toEntity());
            return;
        }
        BuskerSns existing = buskerSnsRepository.findByBuskerUuidAndSnsUrlAndDeletedFalse(requestUpsertBuskerSnsDto.getBuskerUuid(), oldSnsUrl)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER_SNS));

        existing.updateSnsUrl(requestUpsertBuskerSnsDto.getSnsUrl());
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
