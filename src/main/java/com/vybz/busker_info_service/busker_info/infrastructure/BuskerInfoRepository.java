package com.vybz.busker_info_service.busker_info.infrastructure;

import com.vybz.busker_info_service.busker_info.domain.BuskerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuskerInfoRepository extends JpaRepository<BuskerInfo, Long> {

    /**
     * 버스커 uuid로 삭제되지 않은 버스커 조회
     *
     * @param buskerUuid
     */
    Optional<BuskerInfo> findByBuskerUuidAndDeletedFalse(String buskerUuid);

    /**
     * 버스커 uuid로 버스커 존재 여부 확인
     * @param buskerUuid
     */
    boolean existsByBuskerUuid(String buskerUuid);

    /**
     * 삭제되지 않은 모든 버스커 조회
     */
    List<BuskerInfo> findAllByDeletedFalse();

    List<BuskerInfo> findByBuskerUuidIn(List<String> buskerUuid);

}
