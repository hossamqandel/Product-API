package com.example.product_api;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApiApplication.class, args);

            ModelMapper modelMapper = new ModelMapper();
            Player playerEntity = new Player(1L, "Salma", "Cairo");
            PlayerDto playerDto = modelMapper.map(playerEntity, PlayerDto.class);
            System.out.println("Player Dto Id = " + playerDto.getId());
            System.out.println("Player Dto Phone = " + playerDto.getPhone());

    }

}
