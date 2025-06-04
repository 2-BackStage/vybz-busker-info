package com.vybz.busker_info_service.busker_group.dto.request;

import com.vybz.busker_info_service.busker_group.vo.request.RequestDeleteBuskerGroupVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteBuskerGroupDto {

    private String groupUuid;
    private String buskerUuid;

    @Builder
    public RequestDeleteBuskerGroupDto(String groupUuid, String buskerUuid) {
        this.groupUuid = groupUuid;
        this.buskerUuid = buskerUuid;
    }

    public static RequestDeleteBuskerGroupDto from(RequestDeleteBuskerGroupVo requestDeleteBuskerGroupVo) {
        return RequestDeleteBuskerGroupDto.builder()
                .groupUuid(requestDeleteBuskerGroupVo.getGroupUuid())
                .buskerUuid(requestDeleteBuskerGroupVo.getBuskerUuid())
                .build();
    }

}
