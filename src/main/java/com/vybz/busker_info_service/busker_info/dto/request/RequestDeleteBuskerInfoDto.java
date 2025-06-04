package com.vybz.busker_info_service.busker_info.dto.request;

import com.vybz.busker_info_service.busker_info.vo.request.RequestDeleteBuskerInfoVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteBuskerInfoDto {

    private String buskerUuid;

    @Builder
    public RequestDeleteBuskerInfoDto(String buskerUuid) {
        this.buskerUuid = buskerUuid;
    }

    public static RequestDeleteBuskerInfoDto from(RequestDeleteBuskerInfoVo requestDeleteBuskerInfoVo) {
        return RequestDeleteBuskerInfoDto.builder()
                .buskerUuid(requestDeleteBuskerInfoVo.getBuskerUuid())
                .build();
    }

}
