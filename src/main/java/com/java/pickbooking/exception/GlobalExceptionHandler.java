package com.java.pickbooking.exception;

import com.java.pickbooking.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMesage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        //System.out.println(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = PostException.class)
    ResponseEntity<ApiResponse> handlingPostException(PostException postException){
        ErrorCode errorCode = postException.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMesage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidateException(MethodArgumentNotValidException exception){
        ApiResponse apiResponse = new ApiResponse();
        String result = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.valueOf(result);
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMesage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
