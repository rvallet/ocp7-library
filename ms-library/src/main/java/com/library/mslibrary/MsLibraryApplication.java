package com.library.mslibrary;

import com.library.mslibrary.entities.User;
import com.library.mslibrary.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
public class MsLibraryApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsLibraryApplication.class);

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MsLibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean isBddInit = false;

		/* Initialize BDD with sample test users if empty (on first launch only) */
		LOGGER.info("Recherche de l'existance des utilisateurs en BDD");

		if (CollectionUtils.isEmpty(userRepository.findAll())) {
			LOGGER.info("Création d'un jeu de données utilisateur de test (table 'user')");
			isBddInit=true;

			List<User> userList = Arrays.asList(
					new User(
							"email@user1.fr",
							"user1_lastName",
							"user1_firstName",
							"passwordUser1",
							"user"
					),
					new User(
							"email@user2.fr",
							"user2_lastName",
							"user2_firstName",
							"passwordUser2",
							"user"
					)
			);

			userRepository.saveAll(userList);
			LOGGER.info("Ajout de {} Utilisateurs", userList.size());
		} else {
			LOGGER.debug("Des utilisateurs existent déjà en BDD");
		}
	}

}
