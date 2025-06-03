package com.vybz.busker_info_service.busker_info.application;

import com.vybz.busker_info_service.busker_info.domain.BuskerInfo;
import com.vybz.busker_info_service.busker_info.dto.request.RequestAddBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.request.RequestDeleteBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.request.RequestUpdateBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.dto.response.ResponseBuskerInfoDto;
import com.vybz.busker_info_service.busker_info.infrastructure.BuskerInfoRepository;
import com.vybz.busker_info_service.common.entity.BaseResponseStatus;
import com.vybz.busker_info_service.common.exception.BaseException;
import com.vybz.busker_info_service.common.util.AmazonS3UploaderUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BuskerInfoServiceImpl implements BuskerInfoService {

    private final BuskerInfoRepository buskerInfoRepository;
    private final AmazonS3UploaderUtil amazonS3UploaderUtil;

    /**
     * 버스커 정보 추가
     *
     * @param requestAddBuskerInfoDto
     */
    @Transactional
    @Override
    public void createBuskerInfo(RequestAddBuskerInfoDto requestAddBuskerInfoDto) {
        if (buskerInfoRepository.existsByBuskerUuid(requestAddBuskerInfoDto.getBuskerUuid())) {
            throw new BaseException(BaseResponseStatus.DUPLICATE_BUSKER);
        }

        String profileImageUrl = null;

        if (requestAddBuskerInfoDto.getProfileImageUrl() != null && !requestAddBuskerInfoDto.getProfileImageUrl().isEmpty()) {
            profileImageUrl = amazonS3UploaderUtil.upload(requestAddBuskerInfoDto.getProfileImageUrl(), "busker-profile");
        }

        buskerInfoRepository.save(requestAddBuskerInfoDto.toEntity(profileImageUrl));

    }

    /**
     * buskerUuid 버스커 정보 조회
     *
     * @param buskerUuid
     */
    @Override
    public ResponseBuskerInfoDto getBuskerInfoByBuskerUuid(String buskerUuid) {
        BuskerInfo buskerInfo = buskerInfoRepository.findByBuskerUuidAndDeletedFalse(buskerUuid)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER));
        return ResponseBuskerInfoDto.from(buskerInfo);
    }

    /**
     * 모든 버스커 정보 조회
     */
    @Override
    public List<ResponseBuskerInfoDto> getAllBuskerInfo() {
        return buskerInfoRepository.findAllByDeletedFalse()
                .stream()
                .map(ResponseBuskerInfoDto::from)
                .toList();
    }

    /**
     * 버스커 정보 수정
     *
     * @param requestUpdateBuskerInfoDto
     */
    @Transactional
    @Override
    public void updateBuskerInfo(RequestUpdateBuskerInfoDto requestUpdateBuskerInfoDto) {
        BuskerInfo buskerInfo = buskerInfoRepository.findByBuskerUuidAndDeletedFalse(requestUpdateBuskerInfoDto.getBuskerUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER));

        String imageUrl = null;
        MultipartFile profileImage = requestUpdateBuskerInfoDto.getProfileImageUrl();

        if (profileImage != null && !profileImage.isEmpty()) {
            Optional.ofNullable(buskerInfo.getProfileImageUrl())
                    .ifPresent(amazonS3UploaderUtil::delete);
            imageUrl = amazonS3UploaderUtil.upload(profileImage, "busker-profile");
        }
        requestUpdateBuskerInfoDto.updateEntity(buskerInfo, imageUrl);

    }

    /**
     * 버스커 정보 삭제
     *
     * @param requestDeleteBuskerInfoDto
     */
    @Transactional
    @Override
    public void deleteBuskerInfo(RequestDeleteBuskerInfoDto requestDeleteBuskerInfoDto) {
        BuskerInfo buskerInfo = buskerInfoRepository.findByBuskerUuidAndDeletedFalse(requestDeleteBuskerInfoDto.getBuskerUuid())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_BUSKER));
        buskerInfo.softDelete();
    }
}
