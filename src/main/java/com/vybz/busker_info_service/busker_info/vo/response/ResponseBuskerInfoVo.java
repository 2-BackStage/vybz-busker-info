package com.vybz.busker_info_service.busker_info.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseBuskerInfoVo {

    private String buskerUuid;
    private String profileImageUrl;
    private String nickname;
    private String introduction;

    @Builder
    public ResponseBuskerInfoVo(String buskerUuid, String profileImageUrl, String nickname, String introduction) {
        this.buskerUuid = buskerUuid;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.introduction = introduction;
    }

}
