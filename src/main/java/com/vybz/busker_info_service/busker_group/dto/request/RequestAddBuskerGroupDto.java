package com.vybz.busker_info_service.busker_group.dto.request;

import com.vybz.busker_info_service.busker_group.domain.BuskerGroup;
import com.vybz.busker_info_service.busker_group.domain.BuskerState;
import com.vybz.busker_info_service.busker_group.vo.request.RequestAddBuskerGroupVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddBuskerGroupDto {

    private String buskerUuid;
    private String groupUuid;

    @Builder
    public RequestAddBuskerGroupDto(String groupUuid, String buskerUuid) {
        this.groupUuid = groupUuid;
        this.buskerUuid = buskerUuid;
    }

    public BuskerGroup toEntity() {
        return BuskerGroup.builder()
                .groupUuid(groupUuid)
                .buskerUuid(buskerUuid)
                .state(BuskerState.PENDING)
                .build();
    }

    public static RequestAddBuskerGroupDto from(RequestAddBuskerGroupVo requestAddBuskerGroupVo) {
        return RequestAddBuskerGroupDto.builder()
                .groupUuid(requestAddBuskerGroupVo.getGroupUuid())
                .buskerUuid(requestAddBuskerGroupVo.getBuskerUuid())
                .build();
    }

}
