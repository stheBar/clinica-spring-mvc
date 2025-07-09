package br.ufsm.csi.aulaspringmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AulaSpringMvcApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(AulaSpringMvcApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(AulaSpringMvcApplication.class, args);
	}

}
