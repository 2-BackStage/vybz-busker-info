package com.vybz.busker_info_service.busker_info.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BuskerSummary {

    private String buskerUuid;
    private String nickname;
    private String profileImageUrl;

    @Builder
    public BuskerSummary(String buskerUuid, String nickname, String profileImageUrl) {
        this.buskerUuid = buskerUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

}
