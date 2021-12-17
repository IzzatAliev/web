package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;
import ua.com.alevel.config.jpa.DatasourceProperties;
import ua.com.alevel.config.jpa.JpaConfig;

@SpringBootApplication
@EnableConfigurationProperties(DatasourceProperties.class)
public class WebJdbcServerApplication {

	private final JpaConfig jpaConfig;

	public WebJdbcServerApplication(JpaConfig jpaConfig) {
		this.jpaConfig = jpaConfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebJdbcServerApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initDB() {
		jpaConfig.connect();
	}
}
