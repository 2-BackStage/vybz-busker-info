package com.vybz.busker_info_service.busker_category.dto.request;

import com.vybz.busker_info_service.busker_category.domain.BuskerCategory;
import com.vybz.busker_info_service.busker_category.vo.request.RequestAddBuskerCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestAddBuskerCategoryDto {

    private String buskerUuid;
    private Long categoryId;

    @Builder
    public RequestAddBuskerCategoryDto(String buskerUuid, Long categoryId) {
        this.buskerUuid = buskerUuid;
        this.categoryId = categoryId;
    }

    public BuskerCategory toEntity() {
        return BuskerCategory.builder()
                .buskerUuid(buskerUuid)
                .categoryId(categoryId)
                .build();
    }

    public static RequestAddBuskerCategoryDto from(RequestAddBuskerCategoryVo requestAddBuskerCategoryVo) {
        return RequestAddBuskerCategoryDto.builder()
                .buskerUuid(requestAddBuskerCategoryVo.getBuskerUuid())
                .categoryId(requestAddBuskerCategoryVo.getCategoryId())
                .build();
    }

}
