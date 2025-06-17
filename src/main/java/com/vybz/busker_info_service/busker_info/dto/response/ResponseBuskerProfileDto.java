package com.vybz.busker_info_service.busker_info.dto.response;

import com.vybz.busker_info_service.busker_info.vo.response.ResponseBuskerProfileVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseBuskerProfileDto {

    private String nickname;

    private String profileImageUrl;

    @Builder
    public ResponseBuskerProfileDto(String nickname, String profileImageUrl) {
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
    }

    public ResponseBuskerProfileVo toResponseBuskerProfileVo() {
        return ResponseBuskerProfileVo.builder()
                .profileImageUrl(profileImageUrl)
                .nickname(nickname)
                .build();
    }
}
