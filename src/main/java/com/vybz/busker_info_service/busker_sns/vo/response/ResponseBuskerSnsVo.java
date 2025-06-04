package com.vybz.busker_info_service.busker_sns.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseBuskerSnsVo {

    private String buskerUuid;
    private String snsUrl;

    @Builder
    public ResponseBuskerSnsVo(String buskerUuid, String snsUrl) {
        this.buskerUuid = buskerUuid;
        this.snsUrl = snsUrl;
    }

}
