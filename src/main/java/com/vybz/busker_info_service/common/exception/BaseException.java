package com.vybz.busker_info_service.common.exception;

import com.vybz.busker_info_service.common.entity.BaseResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private final BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        this.status = status;
    }
}
