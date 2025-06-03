package com.vybz.busker_info_service.busker_group.application;

import com.vybz.busker_info_service.busker_group.domain.BuskerGroup;
import com.vybz.busker_info_service.busker_group.dto.request.RequestAddBuskerGroupDto;
import com.vybz.busker_info_service.busker_group.dto.request.RequestDeleteBuskerGroupDto;
import com.vybz.busker_info_service.busker_group.dto.request.RequestUpdateBuskerGroupDto;
import com.vybz.busker_info_service.busker_group.dto.response.ResponseBuskerGroupDto;
import com.vybz.busker_info_service.busker_group.infrastructure.BuskerGroupRepository;
import com.vybz.busker_info_service.common.entity.BaseResponseStatus;
import com.vybz.busker_info_service.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuskerGroupServiceImpl implements BuskerGroupService {

    private final BuskerGroupRepository buskerGroupRepository;

    /**
     * 버스커 그룹 멤버 추가
     * @param requestAddBuskerGroupDto
     */
    @Transactional
    @Override
    public void createBuskerGroup(RequestAddBuskerGroupDto requestAddBuskerGroupDto) {
        if(buskerGroupRepository.existsByGroupUuidAndBuskerUuidAndDeletedFalse(requestAddBuskerGroupDto.getGroupUuid(), requestAddBuskerGroupDto.getBuskerUuid())) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_BUSKER_MEMBER);
        }
        buskerGroupRepository.save(requestAddBuskerGroupDto.toEntity());
    }

    /**
     * 버스커 그룹에 있는 멤버 조회
     * @param groupUuid
     * @param buskerUuid
     */
    @Override
    public ResponseBuskerGroupDto getBuskerGroupAndBuskerMember(String groupUuid, String buskerUuid) {
        BuskerGroup buskerGroup = buskerGroupRepository.findByGroupUuidAndBuskerUuidAndDeletedFalse(groupUuid, buskerUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER_MEMBER));
        return ResponseBuskerGroupDto.from(buskerGroup);
    }

    /**
     * 버스커 그룹에 속한 멤버 리스트 조회
     * @param groupUuid
     */
    @Override
    public List<ResponseBuskerGroupDto> getBuskerGroupByGroupUuid(String groupUuid) {
        return buskerGroupRepository.findByGroupUuidAndDeletedFalse(groupUuid)
                .stream()
                .map(ResponseBuskerGroupDto::from)
                .toList();
    }

    /**
     * 유저가 속한 버스커 그룹 리스트 조회
     * @param buskerUuid
     */
    @Override
    public List<ResponseBuskerGroupDto> getBuskerGroupByBuskerUuid(String buskerUuid) {
        return buskerGroupRepository.findByBuskerUuidAndDeletedFalse(buskerUuid)
                .stream()
                .map(ResponseBuskerGroupDto::from)
                .toList();
    }

    /**
     * 버스커 그룹 멤버 상태 수정
     * @param requestUpdateBuskerGroupDto
     */
    @Transactional
    @Override
    public void updateBuskerGroup(RequestUpdateBuskerGroupDto requestUpdateBuskerGroupDto) {
        BuskerGroup buskerGroup = buskerGroupRepository.findByGroupUuidAndBuskerUuidAndDeletedFalse(
                requestUpdateBuskerGroupDto.getGroupUuid(), requestUpdateBuskerGroupDto.getBuskerUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER_MEMBER));
        buskerGroup.updateState(requestUpdateBuskerGroupDto.getState());
    }

    /**
     * 버스커 그룹 멤버 삭제
     * @param requestDeleteBuskerGroupDto
     */
    @Transactional
    @Override
    public void deleteBuskerGroup(RequestDeleteBuskerGroupDto requestDeleteBuskerGroupDto) {
        BuskerGroup buskerGroup = buskerGroupRepository.findByGroupUuidAndBuskerUuidAndDeletedFalse(
                requestDeleteBuskerGroupDto.getGroupUuid(), requestDeleteBuskerGroupDto.getBuskerUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER_MEMBER));
        buskerGroup.softDelete();
    }
}
