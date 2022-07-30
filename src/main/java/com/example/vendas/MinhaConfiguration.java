package com.example.vendas;

import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class MinhaConfiguration {

    @Bean(name = "applicationName")
    public String applicationName(){
        return "Aplicação de Sistemas de Vendas";
    }
}
