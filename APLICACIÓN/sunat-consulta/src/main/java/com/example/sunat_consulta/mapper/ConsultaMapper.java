package com.example.sunat_consulta.mapper;

import com.example.sunat_consulta.dto.ConsultaResponse;
import com.example.sunat_consulta.entity.Consulta;

public class ConsultaMapper {
    public static ConsultaResponse toDto(Consulta consulta) {
        return new ConsultaResponse(
                consulta.getRucConsultado(),
                consulta.getResultado().name(),
                consulta.getMensajeError(),
                consulta.getProviderStatusCode(),
                consulta.getCreatedAt().toString()
        );
    }
}
