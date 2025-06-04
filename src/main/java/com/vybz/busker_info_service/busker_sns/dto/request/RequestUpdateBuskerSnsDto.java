package com.vybz.busker_info_service.busker_sns.dto.request;

import com.vybz.busker_info_service.busker_sns.vo.request.RequestUpdateBuskerSnsVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpdateBuskerSnsDto {

    private String buskerUuid;
    private String oldSnsUrl;
    private String newSnsUrl;

    @Builder
public RequestUpdateBuskerSnsDto(String buskerUuid, String oldSnsUrl, String newSnsUrl) {
        this.buskerUuid = buskerUuid;
        this.oldSnsUrl = oldSnsUrl;
        this.newSnsUrl = newSnsUrl;
    }

    public static RequestUpdateBuskerSnsDto from(RequestUpdateBuskerSnsVo requestUpdateBuskerSnsVo) {
        return RequestUpdateBuskerSnsDto.builder()
                .buskerUuid(requestUpdateBuskerSnsVo.getBuskerUuid())
                .oldSnsUrl(requestUpdateBuskerSnsVo.getOldSnsUrl())
                .newSnsUrl(requestUpdateBuskerSnsVo.getNewSnsUrl())
                .build();
    }

}
