package com.vybz.busker_info_service.busker_category.dto.response;

import com.vybz.busker_info_service.busker_category.domain.BuskerCategory;
import com.vybz.busker_info_service.busker_category.vo.response.ResponseBuskerCategoryVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseBuskerCategoryDto {

    private String buskerUuid;
    private Long categoryId;

    @Builder
    public ResponseBuskerCategoryDto(String buskerUuid, Long categoryId) {
        this.buskerUuid = buskerUuid;
        this.categoryId = categoryId;
    }

    public static ResponseBuskerCategoryDto from(BuskerCategory buskerCategory) {
        return ResponseBuskerCategoryDto.builder()
                .buskerUuid(buskerCategory.getBuskerUuid())
                .categoryId(buskerCategory.getCategoryId())
                .build();
    }

    public ResponseBuskerCategoryVo toVo() {
        return ResponseBuskerCategoryVo.builder()
                .buskerUuid(buskerUuid)
                .categoryId(categoryId)
                .build();
    }

}
