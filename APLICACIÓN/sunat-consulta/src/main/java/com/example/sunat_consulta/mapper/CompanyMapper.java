package com.example.sunat_consulta.mapper;

import com.example.sunat_consulta.dto.CompanyResponse;
import com.example.sunat_consulta.dto.SunatRucResponse;
import com.example.sunat_consulta.entity.Company;
import com.example.sunat_consulta.enums.CondicionDomicilio;
import com.example.sunat_consulta.enums.EstadoContribuyente;

public class CompanyMapper {

    public static Company toEntity(SunatRucResponse response) {
        Company company = new Company();
        company.setRuc(response.numero_documento());
        updateEntity(company, response);
        return company;
    }

    public static void updateEntity(Company company, SunatRucResponse dto) {

        company.setRazonSocial(dto.razon_social());
        company.setDireccion(dto.direccion());
        company.setUbigeo(dto.ubigeo());
        company.setDepartamento(dto.departamento());
        company.setProvincia(dto.provincia());
        company.setDistrito(dto.distrito());
        company.setEsAgenteRetencion(dto.es_agente_retencion());
        company.setEsBuenContribuyente(dto.es_buen_contribuyente());

        company.setEstado(EstadoContribuyente.from(dto.estado()));
        company.setCondicion(CondicionDomicilio.from(dto.condicion()));
    }

    public static CompanyResponse toDto(Company company) {
        return new CompanyResponse(
                company.getRuc(),
                company.getRazonSocial(),
                company.getEstado() != null ? company.getEstado().name() : null,
                company.getCondicion() != null ? company.getCondicion().name() : null,
                company.getDireccion()
        );
    }

}
