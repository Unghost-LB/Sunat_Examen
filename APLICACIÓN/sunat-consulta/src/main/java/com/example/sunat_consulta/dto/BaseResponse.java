package com.example.sunat_consulta.dto;

public class BaseResponse <T>{

    private int code;
    private String message;
    private T objeto;

    public BaseResponse(int code, String message, T objeto) {
        this.code = code;
        this.message = message;
        this.objeto = objeto;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }
}
