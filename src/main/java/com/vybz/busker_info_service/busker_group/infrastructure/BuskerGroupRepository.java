package com.vybz.busker_info_service.busker_group.infrastructure;

import com.vybz.busker_info_service.busker_group.domain.BuskerGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuskerGroupRepository extends JpaRepository<BuskerGroup, Long> {

    /**
     * 버스커 그룹에 멤버가 존재하는지 확인
     * @param groupUuid
     * @param buskerUuid
     */
    boolean existsByGroupUuidAndBuskerUuidAndDeletedFalse(String groupUuid, String buskerUuid);

    /**
     * 버스커 그룹에 있는 멤버 조회
     * @param groupUuid
     * @param buskerUuid
     */
    Optional<BuskerGroup> findByGroupUuidAndBuskerUuidAndDeletedFalse(String groupUuid, String buskerUuid);

    /**
     * groupUuid로 해당 그룹에 속한 멤버 리스트 조회
     * @param groupUuid
     */
    List<BuskerGroup> findByGroupUuidAndDeletedFalse(String groupUuid);

    /**
     * userUuid로 해당 유저가 속한 그룹 리스트 조회
     * @param buskerUuid
     */
    List<BuskerGroup> findByBuskerUuidAndDeletedFalse(String buskerUuid);

}
