package com.vybz.busker_info_service.busker_info.vo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class RequestUpdateBuskerInfoVo {

    private String buskerUuid;
    private MultipartFile profileImageUrl;
    private String nickname;
    private String introduction;

}
