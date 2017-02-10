package org.bitbucket.eniqen;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@SpringBootApplication(scanBasePackages = "org.bitbucket.eniqen")
@EntityScan("org.bitbucket.eniqen.models")
@EnableJpaRepositories("org.bitbucket.eniqen.repositories")
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 3; i++) {
			System.out.println(UUID.randomUUID().toString());
		}
	}
}
