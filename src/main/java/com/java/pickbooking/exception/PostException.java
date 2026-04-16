package com.java.pickbooking.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostException extends RuntimeException{

    private ErrorCode errorCode;

    public PostException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
