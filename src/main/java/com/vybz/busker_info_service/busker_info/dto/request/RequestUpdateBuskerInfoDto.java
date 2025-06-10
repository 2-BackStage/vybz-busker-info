package com.vybz.busker_info_service.busker_info.dto.request;

import com.vybz.busker_info_service.busker_info.domain.BuskerInfo;
import com.vybz.busker_info_service.busker_info.vo.request.RequestUpdateBuskerInfoVo;
import com.vybz.busker_info_service.kafka.event.BuskerInfoEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateBuskerInfoDto {

    private String buskerUuid;
    private String profileImageUrl;
    private String nickname;
    private String introduction;

    @Builder
    public RequestUpdateBuskerInfoDto(String buskerUuid, String profileImageUrl, String nickname, String introduction) {
        this.buskerUuid = buskerUuid;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.introduction = introduction;
    }

    public BuskerInfo updateEntity(BuskerInfo buskerInfo) {
        return BuskerInfo.builder()
                .id(buskerInfo.getId())
                .buskerUuid(buskerUuid)
                .profileImageUrl(profileImageUrl)
                .nickname(nickname)
                .introduction(introduction)
                .build();
    }

    public static RequestUpdateBuskerInfoDto from(RequestUpdateBuskerInfoVo requestUpdateBuskerInfoVo) {
        return RequestUpdateBuskerInfoDto.builder()
                .buskerUuid(requestUpdateBuskerInfoVo.getBuskerUuid())
                .profileImageUrl(requestUpdateBuskerInfoVo.getProfileImageUrl())
                .nickname(requestUpdateBuskerInfoVo.getNickname())
                .introduction(requestUpdateBuskerInfoVo.getIntroduction())
                .build();
    }

    public static BuskerInfoEvent toBuskerInfoEvent(BuskerInfo buskerInfo) {
        return BuskerInfoEvent.builder()
                .buskerUuid(buskerInfo.getBuskerUuid())
                .nickname(buskerInfo.getNickname())
                .profileImageUrl(buskerInfo.getProfileImageUrl())
                .introduction(buskerInfo.getIntroduction())
                .build();
    }

}
