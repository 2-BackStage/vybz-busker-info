package com.vybz.busker_info_service.busker_info.dto.response;

import com.vybz.busker_info_service.busker_info.domain.BuskerInfo;
import com.vybz.busker_info_service.busker_info.vo.response.ResponseBuskerInfoVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseBuskerInfoDto {

    private String buskerUuid;
    private String profileImageUrl;
    private String nickname;
    private String introduction;

    @Builder
    public ResponseBuskerInfoDto(String buskerUuid, String profileImageUrl, String nickname, String introduction) {
        this.buskerUuid = buskerUuid;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.introduction = introduction;
    }

    public static ResponseBuskerInfoDto from(BuskerInfo buskerInfo) {
        return ResponseBuskerInfoDto.builder()
                .buskerUuid(buskerInfo.getBuskerUuid())
                .profileImageUrl(buskerInfo.getProfileImageUrl())
                .nickname(buskerInfo.getNickname())
                .introduction(buskerInfo.getIntroduction())
                .build();
    }

    public ResponseBuskerInfoVo toVo() {
        return ResponseBuskerInfoVo.builder()
                .buskerUuid(buskerUuid)
                .profileImageUrl(profileImageUrl)
                .nickname(nickname)
                .introduction(introduction)
                .build();
    }

}
