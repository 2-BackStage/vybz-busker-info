package com.vybz.busker_info_service.busker_sns.application;

import com.vybz.busker_info_service.busker_sns.dto.request.RequestUpsertBuskerSnsDto;
import com.vybz.busker_info_service.busker_sns.dto.request.RequestDeleteBuskerSnsDto;
import com.vybz.busker_info_service.busker_sns.dto.response.ResponseBuskerSnsDto;

import java.util.List;

public interface BuskerSnsService {

    /**
     * 버스커 sns 생성/수정
     * @param requestUpsertBuskerSnsDto
     */
    void upsertBuskerSns(RequestUpsertBuskerSnsDto requestUpsertBuskerSnsDto);

    /**
     * 유저 uuid로 버스커 sns 조회
     * @param buskerUuid
     */
    List<ResponseBuskerSnsDto> findBuskerSnsByBuskerUuid(String buskerUuid);

    /**
     * 버스커 sns 삭제
     * @param requestDeleteBuskerSnsDto
     */
    void deleteBuskerSns(RequestDeleteBuskerSnsDto requestDeleteBuskerSnsDto);

}
