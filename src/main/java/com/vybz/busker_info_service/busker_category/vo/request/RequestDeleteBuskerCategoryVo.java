package com.vybz.busker_info_service.busker_category.vo.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestDeleteBuskerCategoryVo {

    private String buskerUuid;
    private Long categoryId;

}
