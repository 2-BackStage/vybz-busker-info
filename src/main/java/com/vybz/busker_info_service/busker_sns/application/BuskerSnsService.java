package com.vybz.busker_info_service.busker_sns.application;

import com.vybz.busker_info_service.busker_sns.dto.request.RequestAddBuskerSnsDto;
import com.vybz.busker_info_service.busker_sns.dto.request.RequestDeleteBuskerSnsDto;
import com.vybz.busker_info_service.busker_sns.dto.request.RequestUpdateBuskerSnsDto;
import com.vybz.busker_info_service.busker_sns.dto.response.ResponseBuskerSnsDto;

import java.util.List;

public interface BuskerSnsService {

    /**
     * 버스커 sns 생성
     * @param requestAddBuskerSnsDto
     */
    void createBuskerSns(RequestAddBuskerSnsDto requestAddBuskerSnsDto);

    /**
     * 유저 uuid로 버스커 sns 조회
     * @param buskerUuid
     */
    List<ResponseBuskerSnsDto> findBuskerSnsByBuskerUuid(String buskerUuid);

    /**
     * 버스커 sns 업데이트
     * @param requestUpdateBuskerSnsDto
     */
    void updateBuskerSns(RequestUpdateBuskerSnsDto requestUpdateBuskerSnsDto);

    /**
     * 버스커 sns 삭제
     * @param requestDeleteBuskerSnsDto
     */
    void deleteBuskerSns(RequestDeleteBuskerSnsDto requestDeleteBuskerSnsDto);

}
