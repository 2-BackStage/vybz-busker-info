package com.vybz.busker_info_service.busker_category.vo.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResponseBuskerCategoryVo {

    private String buskerUuid;
    private Long categoryId;

    @Builder
    public ResponseBuskerCategoryVo(String buskerUuid, Long categoryId) {
        this.buskerUuid = buskerUuid;
        this.categoryId = categoryId;
    }

}
