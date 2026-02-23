package com.example.sunat_consulta.enums;

public enum EstadoContribuyente {
    ACTIVO,
    BAJA,
    SUSPENDIDO,
    DESCONOCIDO;

    public static EstadoContribuyente from(String value){
        try{
            return EstadoContribuyente.valueOf(value.toUpperCase());
        }catch(Exception e){
            return DESCONOCIDO;
        }
    }
}
