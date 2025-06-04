package com.vybz.busker_info_service.busker_category.infrastructure;

import com.vybz.busker_info_service.busker_category.domain.BuskerCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuskerCategoryRepository extends JpaRepository<BuskerCategory, Long> {

    /**
     * 버스커 uuid로 버스커 카테고리 리스트 조회
     * @param buskerUuid
     */
    List<BuskerCategory> findAllByBuskerUuidAndDeletedFalse(String buskerUuid);

    /**
     * 버스커 uuid와 카테고리 id로 버스커 카테고리 존재 여부 확인
     * @param buskerUuid
     * @param categoryId
     */
    boolean existsByBuskerUuidAndCategoryIdAndDeletedFalse(String buskerUuid, Long categoryId);

    /**
     * 버스커 uuid와 카테고리 id로 버스커 카테고리 조회
     * @param buskerUuid
     * @param categoryId
     */
    Optional<BuskerCategory> findByBuskerUuidAndCategoryIdAndDeletedFalse(String buskerUuid, Long categoryId);

}
