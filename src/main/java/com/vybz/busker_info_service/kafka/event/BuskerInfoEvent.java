package com.vybz.busker_info_service.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BuskerInfoEvent {

    private String buskerUuid;
    private String nickname;
    private String profileImageUrl;
    private String introduction;

    @Builder
    public BuskerInfoEvent(String buskerUuid, String nickname, String profileImageUrl, String introduction) {
        this.buskerUuid = buskerUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.introduction = introduction;
    }

}
