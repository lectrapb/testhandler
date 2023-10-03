package com.app.domain.value;

import com.app.shared.domain.exception.BusinessException;
import com.app.shared.domain.exception.ConstantBusinessException;

public class TodoStatus {

    private Boolean value;

    public TodoStatus(Boolean value) {
        if(value == null){
            throw new BusinessException(ConstantBusinessException.DATA_REQUIRED);
        }
        this.value = value;
    }
}
