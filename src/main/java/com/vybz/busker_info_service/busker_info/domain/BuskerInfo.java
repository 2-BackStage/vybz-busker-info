package com.vybz.busker_info_service.busker_info.domain;

import com.vybz.busker_info_service.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "busker_info")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class BuskerInfo extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 버스커 uuid
     */
    @Column(name = "busker_uuid", nullable = false, unique = true)
    private String buskerUuid;

    /**
     * 버스커 프로필 사진 URL
     * */
    @Column(name = "profile_image_url")
    private String profileImageUrl;

    /**
     * 버스커 활동명
     * */
    @Column(name = "nickname", nullable = false, length = 15)
    private String nickname;

    /**
     * 소개
     */
    @Column(name = "introduction")
    private String introduction;

    @Builder
    public BuskerInfo(Long id, String buskerUuid, String profileImageUrl, String nickname, String introduction) {
        this.id = id;
        this.buskerUuid = buskerUuid;
        this.profileImageUrl = profileImageUrl;
        this.nickname = nickname;
        this.introduction = introduction;
    }

}
