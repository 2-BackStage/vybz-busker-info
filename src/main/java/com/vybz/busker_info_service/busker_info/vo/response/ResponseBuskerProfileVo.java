package com.vybz.busker_info_service.busker_info.vo.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseBuskerProfileVo {

    private String nickname;

    private String profileImageUrl;

    @Builder
    public ResponseBuskerProfileVo(String nickname, String profileImageUrl) {
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }
}
