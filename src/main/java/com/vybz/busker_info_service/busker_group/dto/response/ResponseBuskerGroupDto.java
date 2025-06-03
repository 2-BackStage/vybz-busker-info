package com.vybz.busker_info_service.busker_group.dto.response;

import com.vybz.busker_info_service.busker_group.domain.BuskerGroup;
import com.vybz.busker_info_service.busker_group.domain.BuskerState;
import com.vybz.busker_info_service.busker_group.vo.response.ResponseBuskerGroupVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseBuskerGroupDto {

    private String groupUuid;
    private String buskerUuid;
    private BuskerState state;

    @Builder
    public ResponseBuskerGroupDto(String groupUuid, String buskerUuid, BuskerState state) {
        this.groupUuid = groupUuid;
        this.buskerUuid = buskerUuid;
        this.state = state;
    }

    public static ResponseBuskerGroupDto from(BuskerGroup buskerGroup) {
        return ResponseBuskerGroupDto.builder()
                .groupUuid(buskerGroup.getGroupUuid())
                .buskerUuid(buskerGroup.getBuskerUuid())
                .state(buskerGroup.getState())
                .build();
    }

    public ResponseBuskerGroupVo toVo() {
        return ResponseBuskerGroupVo.builder()
                .groupUuid(groupUuid)
                .buskerUuid(buskerUuid)
                .state(state)
                .build();
    }

}
