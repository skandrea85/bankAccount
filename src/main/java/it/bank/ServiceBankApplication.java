package it.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ServiceBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBankApplication.class, args);
	}

}
