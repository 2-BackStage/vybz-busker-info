package com.vybz.busker_info_service.busker_sns.dto.request;

import com.vybz.busker_info_service.busker_sns.domain.BuskerSns;
import com.vybz.busker_info_service.busker_sns.vo.request.RequestAddBuskerSnsVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddBuskerSnsDto {

    private String buskerUuid;
    private String snsUrl;

    @Builder
    public RequestAddBuskerSnsDto(String buskerUuid, String snsUrl) {
        this.buskerUuid = buskerUuid;
        this.snsUrl = snsUrl;
    }

    public BuskerSns toEntity() {
        return BuskerSns.builder()
                .buskerUuid(buskerUuid)
                .snsUrl(snsUrl)
                .build();
    }

    public static RequestAddBuskerSnsDto from(RequestAddBuskerSnsVo requestAddBuskerSnsVo) {
        return RequestAddBuskerSnsDto.builder()
                .buskerUuid(requestAddBuskerSnsVo.getBuskerUuid())
                .snsUrl(requestAddBuskerSnsVo.getSnsUrl())
                .build();
    }

}
