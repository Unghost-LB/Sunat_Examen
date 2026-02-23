package com.example.sunat_consulta.repository;

import com.example.sunat_consulta.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByRuc(String ruc);
}
