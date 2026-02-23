package com.example.sunat_consulta.enums;

public enum CondicionDomicilio {
    HABIDO,
    NO_HABIDO,
    PENDIENTE,
    DESCONOCIDO;

    public static CondicionDomicilio from(String value){
        try{
            return CondicionDomicilio.valueOf(value.toUpperCase().replace(" ","_"));
        }catch(Exception e){
            return DESCONOCIDO;
        }
    }
}
