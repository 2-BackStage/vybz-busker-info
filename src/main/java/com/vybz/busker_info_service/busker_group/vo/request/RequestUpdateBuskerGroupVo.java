package com.vybz.busker_info_service.busker_group.vo.request;

import com.vybz.busker_info_service.busker_group.domain.BuskerState;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateBuskerGroupVo {

    private String groupUuid;
    private String buskerUuid;
    private BuskerState state;

}
