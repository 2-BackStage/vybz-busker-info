package com.vybz.busker_info_service.busker_group.domain;

import com.vybz.busker_info_service.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "busker_group")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BuskerGroup extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 버스커 uuid
     */
    @Column(name = "busker_uuid", nullable = false)
    private String buskerUuid;

    /**
     * 버스커 그룹 uuid
     */
    @Column(name = "group_uuid", nullable = false)
    private String groupUuid;

    /**
     * 상태
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private BuskerState state = BuskerState.PENDING;

    public void updateState(BuskerState state) {
        this.state = state;
    }

    @Builder
    public BuskerGroup(Long id, String buskerUuid, String groupUuid, BuskerState state) {
        this.id = id;
        this.buskerUuid = buskerUuid;
        this.groupUuid = groupUuid;
        this.state = state;
    }

}
