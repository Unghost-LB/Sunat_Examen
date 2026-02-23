package com.example.sunat_consulta.entity;

import com.example.sunat_consulta.enums.CondicionDomicilio;
import com.example.sunat_consulta.enums.EstadoContribuyente;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11, unique = true)
    private String ruc;

    private String razonSocial;

    @Enumerated(EnumType.STRING)
    private EstadoContribuyente estado;

    @Enumerated(EnumType.STRING)
    private CondicionDomicilio condicion;

    private String direccion;
    private String ubigeo;
    private String departamento;
    private String provincia;
    private String distrito;

    private boolean esAgenteRetencion;
    private boolean esBuenContribuyente;

    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public EstadoContribuyente getEstado() {
        return estado;
    }

    public void setEstado(EstadoContribuyente estado) {
        this.estado = estado;
    }

    public CondicionDomicilio getCondicion() {
        return condicion;
    }

    public void setCondicion(CondicionDomicilio condicion) {
        this.condicion = condicion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public boolean isEsAgenteRetencion() {
        return esAgenteRetencion;
    }

    public void setEsAgenteRetencion(boolean esAgenteRetencion) {
        this.esAgenteRetencion = esAgenteRetencion;
    }

    public boolean isEsBuenContribuyente() {
        return esBuenContribuyente;
    }

    public void setEsBuenContribuyente(boolean esBuenContribuyente) {
        this.esBuenContribuyente = esBuenContribuyente;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @OneToMany(mappedBy = "company")
    private List<Consulta> consultas;

}
