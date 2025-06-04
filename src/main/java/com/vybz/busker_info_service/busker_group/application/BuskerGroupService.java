package com.vybz.busker_info_service.busker_group.application;

import com.vybz.busker_info_service.busker_group.dto.request.RequestAddBuskerGroupDto;
import com.vybz.busker_info_service.busker_group.dto.request.RequestDeleteBuskerGroupDto;
import com.vybz.busker_info_service.busker_group.dto.request.RequestUpdateBuskerGroupDto;
import com.vybz.busker_info_service.busker_group.dto.response.ResponseBuskerGroupDto;

import java.util.List;

public interface BuskerGroupService {

    /**
     * 버스커 멤버 추가
     * @param requestAddBuskerGroupDto
     */
    void createBuskerGroup(RequestAddBuskerGroupDto requestAddBuskerGroupDto);

    /**
     * 버스커 그룹에 있는 멤버 조회
     * @param groupUuid
     * @param buskerUuid
     */
    ResponseBuskerGroupDto getBuskerGroupAndBuskerMember(String groupUuid, String buskerUuid);

    /**
     * 버스커 그룹에 속한 멤버 리스트 조회
     * @param groupUuid
     */
    List<ResponseBuskerGroupDto> getBuskerGroupByGroupUuid(String groupUuid);

    /**
     * 유저가 속한 버스커 그룹 리스트 조회
     * @param buskerUuid
     */
    List<ResponseBuskerGroupDto> getBuskerGroupByBuskerUuid(String buskerUuid);

    /**
     * 버스커 그룹 멤버 상태 수정
     * @param requestUpdateBuskerGroupDto
     */
    void updateBuskerGroup(RequestUpdateBuskerGroupDto requestUpdateBuskerGroupDto);

    /**
     * 버스커 그룹 멤버 삭제
     * @param requestDeleteBuskerGroupDto
     */
    void deleteBuskerGroup(RequestDeleteBuskerGroupDto requestDeleteBuskerGroupDto);

}
