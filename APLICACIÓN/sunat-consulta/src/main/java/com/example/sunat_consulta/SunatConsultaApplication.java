package com.example.sunat_consulta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SunatConsultaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunatConsultaApplication.class, args);
	}

}
