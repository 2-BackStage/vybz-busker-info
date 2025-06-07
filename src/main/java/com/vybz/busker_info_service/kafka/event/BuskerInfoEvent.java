package com.vybz.busker_info_service.kafka.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BuskerInfoEvent {

    private String buskerUuid;
    private String nickname;
    private String profileImageUrl;
    private String introduction;

}
