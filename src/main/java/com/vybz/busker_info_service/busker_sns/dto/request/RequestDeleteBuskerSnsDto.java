package com.vybz.busker_info_service.busker_sns.dto.request;

import com.vybz.busker_info_service.busker_sns.vo.request.RequestDeleteBuskerSnsVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteBuskerSnsDto {

    private String buskerUuid;
    private String snsUrl;

    @Builder
    public RequestDeleteBuskerSnsDto(String buskerUuid, String snsUrl) {
        this.buskerUuid = buskerUuid;
        this.snsUrl = snsUrl;
    }

    public static RequestDeleteBuskerSnsDto from(RequestDeleteBuskerSnsVo requestDeleteBuskerSnsVo) {
        return RequestDeleteBuskerSnsDto.builder()
                .buskerUuid(requestDeleteBuskerSnsVo.getBuskerUuid())
                .snsUrl(requestDeleteBuskerSnsVo.getSnsUrl())
                .build();
    }

}
