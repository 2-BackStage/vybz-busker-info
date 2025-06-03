package com.vybz.busker_info_service.busker_info.dto.request;

import com.vybz.busker_info_service.busker_info.domain.BuskerInfo;
import com.vybz.busker_info_service.busker_info.vo.request.RequestAddBuskerInfoVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class RequestAddBuskerInfoDto {

    private String buskerUuid;
    private MultipartFile profileImageUrl;
    private String nickname;
    private String introduction;

    @Builder
    public RequestAddBuskerInfoDto(String userUuid, MultipartFile profileImageUrl, String nickname, String introduction) {
        this.buskerUuid = userUuid;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.introduction = introduction;
    }

    public BuskerInfo toEntity(String profileImage) {
        return BuskerInfo.builder()
                .buskerUuid(buskerUuid)
                .profileImageUrl(profileImage)
                .nickname(nickname)
                .introduction(introduction)
                .build();
    }

    public static RequestAddBuskerInfoDto from(RequestAddBuskerInfoVo requestAddBuskerInfoVo) {
        return RequestAddBuskerInfoDto.builder()
                .userUuid(requestAddBuskerInfoVo.getBuskerUuid())
                .profileImageUrl(requestAddBuskerInfoVo.getProfileImageUrl())
                .nickname(requestAddBuskerInfoVo.getNickname())
                .introduction(requestAddBuskerInfoVo.getIntroduction())
                .build();
    }

}
