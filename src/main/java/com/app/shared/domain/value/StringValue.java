package com.app.shared.domain.value;

import com.app.shared.domain.exception.BusinessException;
import com.app.shared.domain.exception.ConstantBusinessException;

public abstract class StringValue {

     private String value;

    public StringValue(String value) {
        if(value == null || value.isEmpty()){
            throw new BusinessException(ConstantBusinessException.DATA_REQUIRED);
        }
        this.value = value;
    }

    public String value(){
        return value;
    }
}
