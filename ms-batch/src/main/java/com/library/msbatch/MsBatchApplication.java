package com.library.msbatch;

import com.library.msbatch.config.ApplicationPropertiesConfig;
import com.library.msbatch.config.MailProperties;
import com.library.msbatch.service.EmailService;
import com.library.msbatch.service.impl.EmailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.mail.SimpleMailMessage;

@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
public class MsBatchApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsBatchApplication.class);

	@Autowired
	public EmailService emailService;

	@Autowired
	public SimpleMailMessage template;

	@Autowired
	ApplicationPropertiesConfig applicationPropertiesConfig;

	@Autowired
	MailProperties mailProperties;

	public static void main(String[] args) {
		SpringApplication.run(MsBatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("\nAppProperties => {}", applicationPropertiesConfig.getExemple());
		LOGGER.info("\nMailProperties => \nHost : {} \nPort : {} \nUserName: {} \nPassword : {}",mailProperties.getHost(), mailProperties.getPort(), mailProperties.getUsername(), mailProperties.getPassword());

		String text = String.format(template.getText(), "<h1>Coucou</h1>");
		emailService.sendSimpleMessage("remy.vallet@gmail.com", "subject", text);

	}
}
