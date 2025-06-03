package com.vybz.busker_info_service.busker_group.dto.request;

import com.vybz.busker_info_service.busker_group.domain.BuskerState;
import com.vybz.busker_info_service.busker_group.vo.request.RequestUpdateBuskerGroupVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateBuskerGroupDto {

    private String groupUuid;
    private String buskerUuid;
    private BuskerState state;

    @Builder
    public RequestUpdateBuskerGroupDto(String groupUuid, String buskerUuid, BuskerState state) {
        this.groupUuid = groupUuid;
        this.buskerUuid = buskerUuid;
        this.state = state;
    }

    public static RequestUpdateBuskerGroupDto from(RequestUpdateBuskerGroupVo requestUpdateBuskerGroupVo) {
        return RequestUpdateBuskerGroupDto.builder()
                .groupUuid(requestUpdateBuskerGroupVo.getGroupUuid())
                .buskerUuid(requestUpdateBuskerGroupVo.getBuskerUuid())
                .state(requestUpdateBuskerGroupVo.getState())
                .build();
    }

}
