package com.vybz.busker_info_service.busker_sns.infrastructure;

import com.vybz.busker_info_service.busker_sns.domain.BuskerSns;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BuskerSnsRepository extends JpaRepository<BuskerSns, Long> {

    /**
     * 버스커 uuid와 snsUrl로 버스커 sns 확인
     * @param buskerUuid
     * @param snsUrl
     */
    boolean existsByBuskerUuidAndSnsUrlAndDeletedFalse(String buskerUuid, String snsUrl);

    /**
     * 버스커 uuid와 snsUrl로 버스커 sns 조회
     * @param buskerUuid
     * @param snsUrl
     */
    Optional<BuskerSns> findByBuskerUuidAndSnsUrlAndDeletedFalse(String buskerUuid, String snsUrl);

    /**
     * 버스커 uuid로 버스커 sns 조회
     * @param buskerUuid
     */
    List<BuskerSns> findAllByBuskerUuidAndDeletedFalse(String buskerUuid);

}
