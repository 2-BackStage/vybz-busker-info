package com.vybz.busker_info_service.busker_category.presentation;

import com.vybz.busker_info_service.busker_category.application.BuskerCategoryService;
import com.vybz.busker_info_service.busker_category.dto.request.RequestAddBuskerCategoryDto;
import com.vybz.busker_info_service.busker_category.dto.request.RequestDeleteBuskerCategoryDto;
import com.vybz.busker_info_service.busker_category.dto.request.RequestDeleteBuskerCategoryListDto;
import com.vybz.busker_info_service.busker_category.dto.response.ResponseBuskerCategoryDto;
import com.vybz.busker_info_service.busker_category.vo.request.RequestAddBuskerCategoryVo;
import com.vybz.busker_info_service.busker_category.vo.request.RequestDeleteBuskerCategoryListVo;
import com.vybz.busker_info_service.busker_category.vo.request.RequestDeleteBuskerCategoryVo;
import com.vybz.busker_info_service.busker_category.vo.response.ResponseBuskerCategoryVo;
import com.vybz.busker_info_service.common.entity.BaseResponseEntity;
import com.vybz.busker_info_service.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/busker-category")
public class BuskerCategoryController {

    private final BuskerCategoryService buskerCategoryService;

    /**
     * 버스커 카테고리 생성
     * @param requestAddBuskerCategoryVo
     */
    @Operation(summary = "버스커 카테고리 생성 API", description = "버스커 카테고리 생성 API 입니다.", tags = {"Busker-Category-Service"})
    @PostMapping
    public BaseResponseEntity<Void> createBuskerCategory(@RequestBody RequestAddBuskerCategoryVo requestAddBuskerCategoryVo) {
        buskerCategoryService.createBuskerCategory(RequestAddBuskerCategoryDto.from(requestAddBuskerCategoryVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 버스커 UUID로 버스커 카테고리 리스트 조회
     * @param buskerUuid
     */
    @Operation(summary = "버스커 UUID로 버스커 카테고리 리스트 조회 API", description = "버스커 UUID로 버스커 카테고리 리스트 조회 API 입니다.", tags = {"Busker-Category-Service"})
    @GetMapping("/list/{buskerUuid}")
    public BaseResponseEntity<List<ResponseBuskerCategoryVo>> getBuskerCategoryByBuskerUuid(@PathVariable("buskerUuid") String buskerUuid) {
        List<ResponseBuskerCategoryVo> responseBuskerCategoryVo = buskerCategoryService.getBuskerCategoryByBuskerUuid(buskerUuid)
                .stream()
                .map(ResponseBuskerCategoryDto::toVo)
                .toList();
        return new BaseResponseEntity<>(responseBuskerCategoryVo);
    }

    /**
     * 버스커 UUID와 카테고리 ID로 버스커 카테고리 삭제
     * @param requestDeleteBuskerCategoryVo
     */
    @Operation(summary = "버스커 UUID와 카테고리 ID로 버스커 카테고리 삭제 API", description = "버스커 UUID와 카테고리 ID로 버스커 카테고리 삭제 API 입니다.", tags = {"Busker-Category-Service"})
    @DeleteMapping
    public BaseResponseEntity<Void> deleteBuskerCategoryByBuskerUuidAndCategoryId(@RequestBody RequestDeleteBuskerCategoryVo requestDeleteBuskerCategoryVo) {
        buskerCategoryService.deleteBuskerCategoryByBuskerUuidAndCategoryId(RequestDeleteBuskerCategoryDto.from(requestDeleteBuskerCategoryVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 버스커 UUID로 버스커 카테고리 삭제
     * @param requestDeleteBuskerCategoryListVo
     */
    @Operation(summary = "버스커 UUID로 버스커 카테고리 삭제 API", description = "버스커 UUID로 버스커 카테고리 삭제 API 입니다.", tags = {"Busker-Category-Service"})
    @DeleteMapping("/list")
    public BaseResponseEntity<Void> deleteBuskerCategoryByBuskerUuid(@RequestBody RequestDeleteBuskerCategoryListVo requestDeleteBuskerCategoryListVo) {
        buskerCategoryService.deleteBuskerCategoryByBuskerUuid(RequestDeleteBuskerCategoryListDto.from(requestDeleteBuskerCategoryListVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
