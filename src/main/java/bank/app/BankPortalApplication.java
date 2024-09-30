package bank.app;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "bank.app")
public class BankPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankPortalApplication.class, args);
	}


}
