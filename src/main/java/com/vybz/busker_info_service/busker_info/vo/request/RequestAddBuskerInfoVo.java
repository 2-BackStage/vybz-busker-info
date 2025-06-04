package com.vybz.busker_info_service.busker_info.vo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddBuskerInfoVo {

    private String buskerUuid;
    private String profileImageUrl;
    private String nickname;
    private String introduction;

}
