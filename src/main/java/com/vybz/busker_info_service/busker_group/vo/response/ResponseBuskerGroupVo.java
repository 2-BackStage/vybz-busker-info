package com.vybz.busker_info_service.busker_group.vo.response;

import com.vybz.busker_info_service.busker_group.domain.BuskerState;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseBuskerGroupVo {

    private String groupUuid;
    private String buskerUuid;
    private BuskerState state;

    @Builder
    public ResponseBuskerGroupVo(String groupUuid, String buskerUuid, BuskerState state) {
        this.groupUuid = groupUuid;
        this.buskerUuid = buskerUuid;
        this.state = state;
    }

}
