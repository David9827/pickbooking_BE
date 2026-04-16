package com.java.pickbooking.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ErrorCode {
    ERROR_EXITED(1001, "User already exited!"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Error!"),
    PASSWORD_VALIDATION_ERROR(1003, "Password have been 8 character at least!")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;


}
