package com.library.mslibrary;

import com.library.mslibrary.config.ApplicationPropertiesConfig;
import com.library.mslibrary.entities.Book;
import com.library.mslibrary.entities.BookLoan;
import com.library.mslibrary.entities.User;
import com.library.mslibrary.repository.UserRepository;
import com.library.mslibrary.security.WebSecurityConfig;
import com.library.mslibrary.service.BookLoanService;
import com.library.mslibrary.service.BookService;
import com.library.mslibrary.service.UserService;
import com.library.mslibrary.utils.DateTools;
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
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties
@EnableDiscoveryClient
public class MsLibraryApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(MsLibraryApplication.class);

	@Autowired
	private WebSecurityConfig webSecurityConfig;

	@Autowired
	private ApplicationPropertiesConfig appConfig;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@Autowired
	private BookLoanService bookLoanService;

	public static void main(String[] args) {
		SpringApplication.run(MsLibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		boolean isBddInit = false;

		/* Initialize BDD with sample test users if empty (on first launch only) */
		LOGGER.info("Recherche de l'existance des utilisateurs en BDD");

		if (CollectionUtils.isEmpty(userService.findAll())) {
			LOGGER.info("Création d'un jeu de données utilisateur de test (table 'user')");
			isBddInit=true;

			List<User> userList = Arrays.asList(
					new User(
							"email@user1.fr",
							"user1_lastName",
							"user1_firstName",
							webSecurityConfig.passwordEncoder().encode("passwordUser1"),
							"user"
					),
					new User(
							"email@user2.fr",
							"user2_lastName",
							"user2_firstName",
							webSecurityConfig.passwordEncoder().encode("passwordUser2"),
							"user"
					)
			);

			userService.saveAll(userList);
			LOGGER.info("Ajout de {} Utilisateurs", userList.size());
		} else {
			LOGGER.debug("Des utilisateurs existent déjà en BDD");
		}

		if (isBddInit) {

			/* Initializing BDD with sample test books and loans */

			if (CollectionUtils.isEmpty(bookService.findAll())) {
				LOGGER.info("Création d'un jeu de données de livres en BDD");
				List<Book> bookList = Arrays.asList(
						new Book(
								"title1",
								"description1",
								"shortDescription1",
								"author1",
								"editor1",
								"collection1",
								"isbn1",
								DateTools.addDays(new Date(), -365)
						),
						new Book(
								"title2",
								"description2",
								"shortDescription2",
								"author2",
								"editor2",
								"collection2",
								"isbn2",
								DateTools.addDays(new Date(), -265)
						)
				);
				bookService.saveAll(bookList);
				LOGGER.info("Ajout de {} Livres", bookList.size());
			}

		}
			if (CollectionUtils.isEmpty(bookLoanService.findAll())) {
				LOGGER.info("Création d'un jeu de données d'emprunt de livre en BDD");
				List<BookLoan> bookLoanList = Arrays.asList(
						new BookLoan(
								userService.findUserByEmail("email@user1.fr"),
								bookService.findBookById(1L),
								appConfig.getBookLoanDuration()
						),
						new BookLoan(
								userService.findUserByEmail("email@user2.fr"),
								bookService.findBookById(2L),
								appConfig.getBookLoanDuration()
						)
				);
				bookLoanService.saveAll(bookLoanList);
				LOGGER.info("Ajout de {} prêt de livres", bookLoanList.size());

			}
	}

}
