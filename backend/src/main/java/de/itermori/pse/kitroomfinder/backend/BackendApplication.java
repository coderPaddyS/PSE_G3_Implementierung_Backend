package de.itermori.pse.kitroomfinder.backend;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import java.time.Clock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * The main class.
 *
 * @author Lukas Zetto
 * @author Adriano Castro
 * @version 1.0
 */
@Configuration
@EnableEncryptableProperties
@EnableJpaRepositories(basePackages = "de.itermori.pse.kitroomfinder.backend.repositories")
@SpringBootApplication()
public class BackendApplication {

	/**
	 * The main method.
	 * @param args	The arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public Clock getClock() {
		return Clock.systemDefaultZone();
	}

}

