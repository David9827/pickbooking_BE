package com.java.authmanagerment.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    ERROR_EXITED(1001, "User already exited!"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized Error!"),
    USER_NOT_EXIST(1004, "User not exist!"),
    UNAUTHENTICATED(1005, "Unauthenticated!"),
    PASSWORD_VALIDATION_ERROR(1003, "Password have been 8 character at least!")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;


}
