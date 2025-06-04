package com.vybz.busker_info_service.busker_info.dto.request;

import com.vybz.busker_info_service.busker_info.domain.BuskerInfo;
import com.vybz.busker_info_service.busker_info.vo.request.RequestUpdateBuskerInfoVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class RequestUpdateBuskerInfoDto {

    private String buskerUuid;
    private MultipartFile profileImageUrl;
    private String nickname;
    private String introduction;

    @Builder
    public RequestUpdateBuskerInfoDto(String userUuid, MultipartFile profileImageUrl, String nickname, String introduction) {
        this.buskerUuid = userUuid;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.introduction = introduction;
    }

    public void updateEntity(BuskerInfo buskerInfo, String imageUrl) {
        if (nickname != null) {
            buskerInfo.updateNickname(nickname);
        }
        if (introduction != null) {
            buskerInfo.updateIntroduction(introduction);
        }
        if (profileImageUrl != null && !profileImageUrl.isEmpty() && imageUrl != null) {
            buskerInfo.updateProfileImage(imageUrl);
        }
    }

    public static RequestUpdateBuskerInfoDto from(RequestUpdateBuskerInfoVo requestUpdateBuskerInfoVo) {
        return RequestUpdateBuskerInfoDto.builder()
                .userUuid(requestUpdateBuskerInfoVo.getBuskerUuid())
                .profileImageUrl(requestUpdateBuskerInfoVo.getProfileImageUrl())
                .nickname(requestUpdateBuskerInfoVo.getNickname())
                .introduction(requestUpdateBuskerInfoVo.getIntroduction())
                .build();
    }

}
