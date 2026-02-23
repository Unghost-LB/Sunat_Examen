package com.example.sunat_consulta.repository;

import com.example.sunat_consulta.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
    List<Consulta> findByRucConsultadoOrderByCreatedAtDesc(String ruc);
}
