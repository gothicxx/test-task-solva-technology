package solva.technology.solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SolvaTechnologyBankSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolvaTechnologyBankSystemApplication.class, args);
	}
}
