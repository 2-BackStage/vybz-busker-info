package com.vybz.busker_info_service.busker_sns.dto.request;

import com.vybz.busker_info_service.busker_sns.domain.BuskerSns;
import com.vybz.busker_info_service.busker_sns.vo.request.RequestUpsertBuskerSnsVo;
import com.vybz.busker_info_service.busker_sns.vo.request.RequestAddBuskerSnsVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestUpsertBuskerSnsDto {

    private String buskerUuid;
    private String oldSnsUrl;
    private String snsUrl;

    @Builder
    public RequestUpsertBuskerSnsDto(String buskerUuid, String snsUrl, String oldSnsUrl) {
        this.buskerUuid = buskerUuid;
        this.oldSnsUrl = oldSnsUrl;
        this.snsUrl = snsUrl;
    }

    public BuskerSns toEntity() {
        return BuskerSns.builder()
                .buskerUuid(buskerUuid)
                .snsUrl(snsUrl)
                .build();
    }

    public static RequestUpsertBuskerSnsDto from(RequestAddBuskerSnsVo requestAddBuskerSnsVo) {
        return RequestUpsertBuskerSnsDto.builder()
                .buskerUuid(requestAddBuskerSnsVo.getBuskerUuid())
                .snsUrl(requestAddBuskerSnsVo.getSnsUrl())
                .build();
    }

    public static RequestUpsertBuskerSnsDto from(RequestUpsertBuskerSnsVo requestUpsertBuskerSnsVo) {
        return RequestUpsertBuskerSnsDto.builder()
                .buskerUuid(requestUpsertBuskerSnsVo.getBuskerUuid())
                .oldSnsUrl(requestUpsertBuskerSnsVo.getOldSnsUrl())
                .snsUrl(requestUpsertBuskerSnsVo.getSnsUrl())
                .build();
    }

}
