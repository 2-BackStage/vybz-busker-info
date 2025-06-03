package com.vybz.busker_info_service.busker_group.vo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteBuskerGroupVo {

    private String groupUuid;
    private String buskerUuid;

}
