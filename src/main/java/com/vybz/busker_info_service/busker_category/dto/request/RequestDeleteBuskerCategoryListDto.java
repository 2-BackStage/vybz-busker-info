package com.vybz.busker_info_service.busker_category.dto.request;

import com.vybz.busker_info_service.busker_category.vo.request.RequestDeleteBuskerCategoryListVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteBuskerCategoryListDto {

    private String buskerUuid;

    @Builder
    public RequestDeleteBuskerCategoryListDto(String buskerUuid) {
        this.buskerUuid = buskerUuid;
    }

    public static RequestDeleteBuskerCategoryListDto from(RequestDeleteBuskerCategoryListVo requestDeleteBuskerCategoryListVo) {
        return RequestDeleteBuskerCategoryListDto.builder()
                .buskerUuid(requestDeleteBuskerCategoryListVo.getBuskerUuid())
                .build();
    }

}
