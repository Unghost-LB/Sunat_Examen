package com.example.sunat_consulta.controller;


import com.example.sunat_consulta.dto.CompanyConsultaResponse;
import com.example.sunat_consulta.dto.ConsultaResponse;
import com.example.sunat_consulta.service.SunatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sunat/ruc")
public class CompanyController {

    private final SunatService sunatService;

    public CompanyController(SunatService sunatService) {
        this.sunatService = sunatService;
    }

    @GetMapping("/{ruc}")
    public ResponseEntity<CompanyConsultaResponse> consultarRuc(
            @PathVariable
            String ruc) {

        return ResponseEntity.ok(sunatService.consultarRuc(ruc));
    }

    @GetMapping("/{ruc}/consultas")
    public ResponseEntity<List<ConsultaResponse>> historialConsultas(
            @PathVariable
            String ruc) {

        return ResponseEntity.ok(sunatService.obtenerHistorial(ruc));
    }
}
