package com.example.sunat_consulta.exception;


import com.example.sunat_consulta.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRucException.class)
    public ResponseEntity<BaseResponse<Object>> handleInvalidRuc(InvalidRucException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse<>(4001, ex.getMessage(), null));
    }

    @ExceptionHandler(ExternalServiceException.class)
    public ResponseEntity<BaseResponse<Object>> handleExternal(ExternalServiceException ex){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new BaseResponse<>(4002, ex.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleGeneral(Exception ex) {

        ex.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new BaseResponse<>(5000, "Error interno del servidor", null));
    }

}
