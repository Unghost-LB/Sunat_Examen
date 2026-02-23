package com.example.sunat_consulta.dto;

public record ConsultaResponse(String rucConsultado,
                               String resultado,
                               String mensajeError,
                               Integer providerStatusCode,
                               String fechaConsultada) {
}
