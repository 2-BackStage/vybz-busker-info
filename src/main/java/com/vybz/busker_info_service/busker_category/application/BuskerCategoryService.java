package com.vybz.busker_info_service.busker_category.application;

import com.vybz.busker_info_service.busker_category.dto.request.RequestAddBuskerCategoryDto;
import com.vybz.busker_info_service.busker_category.dto.request.RequestDeleteBuskerCategoryDto;
import com.vybz.busker_info_service.busker_category.dto.request.RequestDeleteBuskerCategoryListDto;
import com.vybz.busker_info_service.busker_category.dto.response.ResponseBuskerCategoryDto;

import java.util.List;

public interface BuskerCategoryService {

    /**
     * 버스커 카테고리 생성
     * @param requestAddBuskerCategoryDto
     */
    void createBuskerCategory(RequestAddBuskerCategoryDto requestAddBuskerCategoryDto);

    /**
     * 버스커 UUID로 버스커 카테고리 조회
     * @param buskerUuid
     */
    List<ResponseBuskerCategoryDto> getBuskerCategoryByBuskerUuid(String buskerUuid);

    /**
     * 버스커 UUID와 카테고리 ID로 버스커 카테고리 삭제
     * @param requestDeleteBuskerCategoryDto
     */
    void deleteBuskerCategoryByBuskerUuidAndCategoryId(RequestDeleteBuskerCategoryDto requestDeleteBuskerCategoryDto);

    /**
     * 버스커 UUID로 모든 버스커 카테고리 삭제
     * @param requestDeleteBuskerCategoryListDto
     */
    void deleteBuskerCategoryByBuskerUuid(RequestDeleteBuskerCategoryListDto requestDeleteBuskerCategoryListDto);

}
