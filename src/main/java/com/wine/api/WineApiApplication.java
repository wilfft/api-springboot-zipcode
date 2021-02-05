package com.wine.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//- Controller com regra de negocio
//- Não uso de interface para contratos
//- Lógica incorreta

@SpringBootApplication
public class WineApiApplication {

    public static void main(String[] args) {
	SpringApplication.run(WineApiApplication.class, args);
    }

}
