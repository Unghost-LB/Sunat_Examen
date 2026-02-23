package com.example.sunat_consulta.client;

import com.example.sunat_consulta.config.SunatFeignConfig;
import com.example.sunat_consulta.dto.SunatRucResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "sunatClient",
        url = "${decolecta.base-url}",
        configuration = SunatFeignConfig.class
)

public interface SunatClient {

    @GetMapping("sunat/ruc")

    SunatRucResponse getRuc(@RequestParam("numero")
                            String numero);
}
