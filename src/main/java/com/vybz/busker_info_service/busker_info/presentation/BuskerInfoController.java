package com.vybz.busker_info_service.busker_info.presentation;

import com.vybz.busker_info_service.busker_info.application.BuskerInfoService;
import com.vybz.busker_info_service.busker_info.dto.request.RequestAddBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.request.RequestDeleteBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.request.RequestUpdateBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.response.ResponseBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.response.ResponseBuskerProfileDto;
import com.vybz.busker_info_service.busker_info.vo.request.RequestAddBuskerInfoVo;
import com.vybz.busker_info_service.busker_info.vo.request.RequestDeleteBuskerInfoVo;
import com.vybz.busker_info_service.busker_info.vo.request.RequestUpdateBuskerInfoVo;
import com.vybz.busker_info_service.busker_info.vo.response.ResponseBuskerInfoVo;
import com.vybz.busker_info_service.busker_info.vo.response.ResponseBuskerProfileVo;
import com.vybz.busker_info_service.common.entity.BaseResponseEntity;
import com.vybz.busker_info_service.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/busker")
public class BuskerInfoController {

    private final BuskerInfoService buskerInfoService;

    /**
     * 버스커 정보 추가
     * @param requestAddBuskerInfoVo
     */
    @Operation(summary = "버스커 정보 추가 API", description = "버스커 정보 추가 API 입니다.", tags = {"Busker-Service"})
    @PostMapping
    public BaseResponseEntity<Void> createBuskerInfo(@RequestBody RequestAddBuskerInfoVo requestAddBuskerInfoVo) {
        buskerInfoService.createBuskerInfo(RequestAddBuskerInfoDto.from(requestAddBuskerInfoVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 버스커 uuid로 버스커 정보 조회
     * @param buskerUuid
     */
    @Operation(summary = "버스커 uuid로 버스커 정보 조회 API", description = "유저 uuid로 버스커 정보 조회 API 입니다.", tags = {"Busker-Service"})
    @GetMapping("/{buskerUuid}")
    public BaseResponseEntity<ResponseBuskerInfoVo> getBuskerInfoByUserUuid(@PathVariable("buskerUuid") String buskerUuid) {
        ResponseBuskerInfoDto responseBuskerInfoDto = buskerInfoService.getBuskerInfoByBuskerUuid(buskerUuid);
        return new BaseResponseEntity<>(responseBuskerInfoDto.toVo());
    }

    /**
     * 모든 버스커 정보 조회
     */
    @Operation(summary = "모든 버스커 정보 조회 API", description = "모든 버스커 정보 조회 API 입니다.", tags = {"Busker-Service"})
    @GetMapping("/list")
    public BaseResponseEntity<List<ResponseBuskerInfoVo>> getAllBuskerInfo() {
        List<ResponseBuskerInfoVo> responseBuskerInfoListVo = buskerInfoService.getAllBuskerInfo()
                .stream()
                .map(ResponseBuskerInfoDto::toVo)
                .toList();
        return new BaseResponseEntity<>(responseBuskerInfoListVo);
    }

    /**
     * 버스커 정보 수정
     * @param requestUpdateBuskerInfoVo
     */
    @Operation(summary = "버스커 정보 수정 API", description = "버스커 정보 수정 API 입니다.", tags = {"Busker-Service"})
    @PutMapping
    public BaseResponseEntity<Void> updateBuskerInfo(@RequestBody RequestUpdateBuskerInfoVo requestUpdateBuskerInfoVo) {
        buskerInfoService.updateBuskerInfo(RequestUpdateBuskerInfoDto.from(requestUpdateBuskerInfoVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 버스커 정보 삭제
     * @param requestDeleteBuskerInfoVo
     */
    @Operation(summary = "버스커 정보 삭제 API", description = "버스커 정보 삭제 API 입니다.", tags = {"Busker-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteBuskerInfo(@RequestBody RequestDeleteBuskerInfoVo requestDeleteBuskerInfoVo) {
        buskerInfoService.deleteBuskerInfo(RequestDeleteBuskerInfoDto.from(requestDeleteBuskerInfoVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(
            summary = "버스커 UUID 통해 이미지, 닉네임 조회 API",
            description = "버스커 UUID 통해 이미지, 닉네임 조회합니다",
            tags = {"Busker-Service"}
    )
    @GetMapping("/profile/{buskerUuid}")
    public BaseResponseEntity<ResponseBuskerProfileVo> getBuskerProfile(
            @PathVariable("buskerUuid") String buskerUuid
    ) {

        ResponseBuskerProfileDto responseBuskerProfileDto = buskerInfoService.getBuskerProfileByUuid(buskerUuid);
        return new BaseResponseEntity<>(responseBuskerProfileDto.toResponseBuskerProfileVo());
    }
}
