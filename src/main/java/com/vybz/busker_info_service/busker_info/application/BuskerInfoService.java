package com.vybz.busker_info_service.busker_info.application;

import com.vybz.busker_info_service.busker_info.dto.request.BuskerSummary;
import com.vybz.busker_info_service.busker_info.dto.request.RequestAddBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.request.RequestDeleteBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.request.RequestUpdateBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.response.ResponseBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.response.ResponseBuskerProfileDto;

import java.util.List;
import java.util.Map;

public interface BuskerInfoService {

    /**
     * 버스커 정보 추가
     * @param requestAddBuskerInfoDto
     */
    void createBuskerInfo(RequestAddBuskerInfoDto requestAddBuskerInfoDto);

    /**
     * buskerUuid로 버스커 정보 조회
     * @param buskerUuid
     */
    ResponseBuskerInfoDto getBuskerInfoByBuskerUuid(String buskerUuid);

    /**
     * 모든 버스커 정보 조회
     */
    List<ResponseBuskerInfoDto> getAllBuskerInfo();

    /**
     * 버스커 정보 수정
     * @param requestUpdateBuskerInfoDto
     */
    void updateBuskerInfo(RequestUpdateBuskerInfoDto requestUpdateBuskerInfoDto);

    /**
     * 버스커 정보 삭제
     * @param requestDeleteBuskerInfoDto
     */
    void deleteBuskerInfo(RequestDeleteBuskerInfoDto requestDeleteBuskerInfoDto);

    /**
     * 버스커 uuid로 버스커 요약 정보 조회
     * @param buskerUuid
     */
    Map<String, BuskerSummary> getUserSummaryBulk(List<String> buskerUuid);

    /**
     * 버스커 프로필 이미지, 닉네임 조회
     * @param buskerUuid
     * @return
     */
    ResponseBuskerProfileDto getBuskerProfileByUuid(String buskerUuid);

}
