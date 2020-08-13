package xml.team7.oglasservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableEurekaClient
public class OglasServiceApplication {

	@RequestMapping("/health")
	public String home(){
		return "Hello world";
	}

	public static void main(String[] args) {
		SpringApplication.run(OglasServiceApplication.class, args);
	}

}
