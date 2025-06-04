package com.vybz.busker_info_service.busker_category.domain;

import com.vybz.busker_info_service.common.entity.SoftDeletableEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "busker_category")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class BuskerCategory extends SoftDeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 버스커 UUID
     */
    @Column(name = "busker_uuid", nullable = false)
    private String buskerUuid;

    /**
     * 카테고리 ID
     */
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Builder
    public BuskerCategory(Long id, String buskerUuid, Long categoryId) {
        this.id = id;
        this.buskerUuid = buskerUuid;
        this.categoryId = categoryId;
    }

}
