package pe.edu.utp.hoteldescansoeterno_v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"pe.edu.utp.hoteldescansoeterno_v2",
		"pe.edu.service",
		"pe.edu.serviceImpl",
		"pe.edu.repository"
})
@EnableJpaRepositories(basePackages = "pe.edu.repository")
@org.springframework.boot.persistence.autoconfigure.EntityScan(basePackages = "pe.edu.entity")
public class HotelDescansoEternoV2Application {
	public static void main(String[] args) {
		SpringApplication.run(HotelDescansoEternoV2Application.class, args);
	}
}