package com.vybz.busker_info_service.busker_category.application;

import com.vybz.busker_info_service.busker_category.domain.BuskerCategory;
import com.vybz.busker_info_service.busker_category.dto.request.RequestAddBuskerCategoryDto;
import com.vybz.busker_info_service.busker_category.dto.request.RequestDeleteBuskerCategoryDto;
import com.vybz.busker_info_service.busker_category.dto.request.RequestDeleteBuskerCategoryListDto;
import com.vybz.busker_info_service.busker_category.dto.response.ResponseBuskerCategoryDto;
import com.vybz.busker_info_service.busker_category.infrastructure.BuskerCategoryRepository;
import com.vybz.busker_info_service.common.entity.BaseResponseStatus;
import com.vybz.busker_info_service.common.exception.BaseException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuskerCategoryServiceImpl implements BuskerCategoryService {

    private final BuskerCategoryRepository buskerCategoryRepository;

    /**
     * 버스커 카테고리 등록
     * @param requestAddBuskerCategoryDto
     */
    @Transactional
    @Override
    public void createBuskerCategory(RequestAddBuskerCategoryDto requestAddBuskerCategoryDto) {
        if(buskerCategoryRepository.existsByBuskerUuidAndCategoryIdAndDeletedFalse(requestAddBuskerCategoryDto.getBuskerUuid(), requestAddBuskerCategoryDto.getCategoryId())) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_BUSKER_CATEGORY);
        }
        buskerCategoryRepository.save(requestAddBuskerCategoryDto.toEntity());
    }

    /**
     * 버스커 UUID로 카테고리 조회
     * @param buskerUuid
     */
    @Override
    public List<ResponseBuskerCategoryDto> getBuskerCategoryByBuskerUuid(String buskerUuid) {
        return buskerCategoryRepository.findAllByBuskerUuidAndDeletedFalse(buskerUuid)
                .stream()
                .map(ResponseBuskerCategoryDto::from)
                .toList();
    }

    /**
     * 버스커 UUID와 카테고리 ID로 카테고리 삭제
     * @param requestDeleteBuskerCategoryDto
     */
    @Transactional
    @Override
    public void deleteBuskerCategoryByBuskerUuidAndCategoryId(RequestDeleteBuskerCategoryDto requestDeleteBuskerCategoryDto) {
        BuskerCategory buskerCategory = buskerCategoryRepository.findByBuskerUuidAndCategoryIdAndDeletedFalse(
                requestDeleteBuskerCategoryDto.getBuskerUuid(), requestDeleteBuskerCategoryDto.getCategoryId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER_OR_CATEGORY));
        buskerCategory.softDelete();
    }

    /**
     * 버스커 UUID로 카테고리 리스트 삭제
     * @param requestDeleteBuskerCategoryListDto
     */
    @Transactional
    @Override
    public void deleteBuskerCategoryByBuskerUuid(RequestDeleteBuskerCategoryListDto requestDeleteBuskerCategoryListDto) {
        List<BuskerCategory> buskerCategory = buskerCategoryRepository.findAllByBuskerUuidAndDeletedFalse(requestDeleteBuskerCategoryListDto.getBuskerUuid());
        if (buskerCategory.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NO_EXIST_BUSKER);
        }
        buskerCategory.forEach(BuskerCategory::softDelete);
    }

}
