package com.vybz.busker_info_service.busker_category.dto.request;

import com.vybz.busker_info_service.busker_category.vo.request.RequestDeleteBuskerCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteBuskerCategoryDto {

    private String buskerUuid;
    private Long categoryId;

    @Builder
    public RequestDeleteBuskerCategoryDto(String buskerUuid, Long categoryId) {
        this.buskerUuid = buskerUuid;
        this.categoryId = categoryId;
    }

    public static RequestDeleteBuskerCategoryDto from(RequestDeleteBuskerCategoryVo requestDeleteBuskerCategoryVo) {
        return RequestDeleteBuskerCategoryDto.builder()
                .buskerUuid(requestDeleteBuskerCategoryVo.getBuskerUuid())
                .categoryId(requestDeleteBuskerCategoryVo.getCategoryId())
                .build();
    }

}
