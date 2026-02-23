package com.example.sunat_consulta.entity;

import com.example.sunat_consulta.enums.ResultadoConsulta;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="consultas")
public class Consulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String rucConsultado;

    @Enumerated(EnumType.STRING)
    private ResultadoConsulta resultado;

    @Column(columnDefinition = "TEXT")
    private String mensajeError;

    private Integer providerStatusCode;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRucConsultado() {
        return rucConsultado;
    }

    public void setRucConsultado(String rucConsultado) {
        this.rucConsultado = rucConsultado;
    }

    public ResultadoConsulta getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoConsulta resultado) {
        this.resultado = resultado;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Integer getProviderStatusCode() {
        return providerStatusCode;
    }

    public void setProviderStatusCode(Integer providerStatusCode) {
        this.providerStatusCode = providerStatusCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne(optional = true)
    @JoinColumn(name = "company_id")
    private Company company;



}
