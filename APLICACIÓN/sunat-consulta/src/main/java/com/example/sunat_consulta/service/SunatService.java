package com.example.sunat_consulta.service;

import com.example.sunat_consulta.client.SunatClient;
import com.example.sunat_consulta.dto.*;
import com.example.sunat_consulta.entity.Company;
import com.example.sunat_consulta.entity.Consulta;
import com.example.sunat_consulta.enums.ResultadoConsulta;
import com.example.sunat_consulta.exception.ExternalServiceException;
import com.example.sunat_consulta.exception.InvalidRucException;
import com.example.sunat_consulta.mapper.CompanyMapper;
import com.example.sunat_consulta.mapper.ConsultaMapper;
import com.example.sunat_consulta.repository.CompanyRepository;
import com.example.sunat_consulta.repository.ConsultaRepository;
import feign.FeignException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SunatService {
    private final CompanyRepository companyRepository;
    private final ConsultaRepository consultaRepository;
    private final SunatClient sunatClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SunatService(CompanyRepository companyRepositorio, ConsultaRepository consultaRepositorio, SunatClient sunatCliente){
        this.companyRepository=companyRepositorio;
        this.consultaRepository=consultaRepositorio;
        this.sunatClient=sunatCliente;
    }

    @Transactional(noRollbackFor = ExternalServiceException.class)
    public CompanyConsultaResponse consultarRuc(String ruc) {

        if (ruc == null || !ruc.matches("\\d{11}")) {
            throw new InvalidRucException("RUC debe tener exactamente 11 dígitos");
        }

        try {

            SunatRucResponse response = sunatClient.getRuc(ruc);


            Company company = companyRepository.findByRuc(ruc)
                    .orElse(new Company());

            CompanyMapper.updateEntity(company, response);
            company.setRuc(ruc);
            company.setCreatedAt(LocalDateTime.now());
            companyRepository.save(company);


            Consulta consulta = new Consulta();
            consulta.setRucConsultado(ruc);
            consulta.setResultado(ResultadoConsulta.SUCCESS);
            consulta.setMensajeError(null);
            consulta.setProviderStatusCode(200);
            consulta.setCreatedAt(LocalDateTime.now());
            consulta.setCompany(company);

            saveConsulta(ruc, ResultadoConsulta.SUCCESS, null, 200, company);

            return new CompanyConsultaResponse(
                    CompanyMapper.toDto(company),
                    ConsultaMapper.toDto(consulta)
            );

        } catch (FeignException ex) {

            String errorMessage = parseFeignError(ex);
            Consulta errorConsulta = new Consulta();
            errorConsulta.setRucConsultado(ruc);
            errorConsulta.setResultado(ResultadoConsulta.ERROR);
            errorConsulta.setMensajeError(errorMessage);
            errorConsulta.setProviderStatusCode(ex.status());
            errorConsulta.setCreatedAt(LocalDateTime.now());

            consultaRepository.save(errorConsulta);

            throw new ExternalServiceException(errorMessage);
        }
    }

    private void saveConsulta(String ruc, ResultadoConsulta resultado, String error, int status, Company company) {
        Consulta consulta = new Consulta();
        consulta.setRucConsultado(ruc);
        consulta.setResultado(resultado);
        consulta.setMensajeError(error);
        consulta.setProviderStatusCode(status);
        consulta.setCreatedAt(LocalDateTime.now());
        consulta.setCompany(company); // Relación ManyToOne
        consultaRepository.save(consulta);
    }

    private String parseFeignError(FeignException ex) {
        try {
            ProviderErrorResponse errorRes = objectMapper.readValue(ex.contentUTF8(), ProviderErrorResponse.class);
            return errorRes.message();
        } catch (Exception e) {
            return "Error del proveedor: " + ex.status();
        }
    }

    public List<ConsultaResponse> obtenerHistorial(String ruc) {

        if (ruc == null || !ruc.matches("\\d{11}")) {
            throw new InvalidRucException("RUC debe tener exactamente 11 dígitos");
        }
        return consultaRepository.findByRucConsultadoOrderByCreatedAtDesc(ruc)
                .stream()
                .map(ConsultaMapper::toDto)
                .toList();
    }

}
