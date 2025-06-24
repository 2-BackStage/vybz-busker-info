package com.vybz.busker_info_service.busker_sns.vo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpsertBuskerSnsVo {

    private String buskerUuid;
    private String oldSnsUrl;
    private String snsUrl;

}
