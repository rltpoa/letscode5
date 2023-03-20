package ada.tech.springclasses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ada.tech.springclasses"})
public class SpringClassesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClassesApplication.class, args);
	}

}
