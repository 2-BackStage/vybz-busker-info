package com.vybz.busker_info_service.kafka.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class BuskerCategoryEvent {

    private String buskerUuid;
    private List<Long> categoryId;

}
