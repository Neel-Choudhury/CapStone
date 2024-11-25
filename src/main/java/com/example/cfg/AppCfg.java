package com.example.cfg;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableTransactionManagement
@EntityScan("com.example.*")
@EnableJpaRepositories("com.example.repo")
public class AppCfg {
	@Bean
	RestTemplate getTemplateHandle() {
		return new RestTemplate();
	}
	
}
