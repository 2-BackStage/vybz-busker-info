package com.vybz.busker_info_service.busker_sns.domain;

import com.vybz.busker_info_service.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "busker_sns")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class BuskerSns extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 버스커 uuid
     */
    @Column(name = "busker_uuid", nullable = false)
    private String buskerUuid;

    /**
     * 버스커 sns Url
     */
    @Column(name = "sns_url", nullable = false)
    private String snsUrl;

    public void updateSnsUrl(String snsUrl) {
        this.snsUrl = snsUrl;
    }

    @Builder
    public BuskerSns(Long id, String buskerUuid, String snsUrl) {
        this.id = id;
        this.buskerUuid = buskerUuid;
        this.snsUrl = snsUrl;
    }

}
