package com.market.backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class WebMarketApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebMarketApplication.class, args);
	}

	@Bean
	public static ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
