package com.vybz.busker_info_service.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BuskerSearchUpdateEvent {

    private String buskerUuid;
    private String nickname;
    private String nicknameChosung;
    private String profileImageUrl;

    @Builder
    public BuskerSearchUpdateEvent(String buskerUuid,
                                   String nickname,
                                   String nicknameChosung,
                                   String profileImageUrl) {
        this.buskerUuid = buskerUuid;
        this.nickname = nickname;
        this.nicknameChosung = nicknameChosung;
        this.profileImageUrl = profileImageUrl;
    }
}
