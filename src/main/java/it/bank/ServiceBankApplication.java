package it.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ServiceBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceBankApplication.class, args);
	}

}
