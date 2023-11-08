package ru.amanibwasp.EquipmentManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:application.yml")
public class EquipmentManagerApplication {


	public static void main(String[] args) {
		SpringApplication.run(EquipmentManagerApplication.class, args);
	}

}
