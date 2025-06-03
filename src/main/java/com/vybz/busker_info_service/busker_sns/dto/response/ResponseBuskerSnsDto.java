package com.vybz.busker_info_service.busker_sns.dto.response;

import com.vybz.busker_info_service.busker_sns.domain.BuskerSns;
import com.vybz.busker_info_service.busker_sns.vo.response.ResponseBuskerSnsVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseBuskerSnsDto {

    private String buskerUuid;
    private String snsUrl;

    @Builder
    public ResponseBuskerSnsDto(String buskerUuid, String snsUrl) {
        this.buskerUuid = buskerUuid;
        this.snsUrl = snsUrl;
    }

    public static ResponseBuskerSnsDto from(BuskerSns buskerSns) {
        return ResponseBuskerSnsDto.builder()
                .buskerUuid(buskerSns.getBuskerUuid())
                .snsUrl(buskerSns.getSnsUrl())
                .build();
    }

    public ResponseBuskerSnsVo toVo() {
        return ResponseBuskerSnsVo.builder()
                .buskerUuid(buskerUuid)
                .snsUrl(snsUrl)
                .build();
    }

}
