package com.vybz.busker_info_service.busker_info.presentation;

import com.vybz.busker_info_service.busker_info.application.BuskerInfoService;
import com.vybz.busker_info_service.busker_info.dto.request.BuskerSummary;
import com.vybz.busker_info_service.busker_info.dto.response.ResponseBuskerInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/busker-info")
public class InternalBuskerInfoController {

    private final BuskerInfoService buskerInfoService;

    @GetMapping("/{buskerUuid}")
    public BuskerSummary getBuskerSummary(@PathVariable("buskerUuid") String buskerUuid) {
        ResponseBuskerInfoDto responseBuskerInfoDto = buskerInfoService.getBuskerInfoByBuskerUuid(buskerUuid);
        return new BuskerSummary(
                responseBuskerInfoDto.getBuskerUuid(),
                responseBuskerInfoDto.getNickname(),
                responseBuskerInfoDto.getProfileImageUrl());
    }

    @PostMapping("/summary-bulk")
    public Map<String, BuskerSummary> getUserSummaryBulk(@RequestBody List<String> buskerUuid) {
        return buskerInfoService.getUserSummaryBulk(buskerUuid);
    }

}
