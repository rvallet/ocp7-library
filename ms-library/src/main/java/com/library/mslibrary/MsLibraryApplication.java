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
import com.library.mslibrary.utils.RandomTools;
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
					),
					new User(
							"email@admin1.fr",
							"admin1_lastName",
							"admin1_firstName",
							webSecurityConfig.passwordEncoder().encode("passwordAdmin1"),
							"admin"
					),
					new User(
							"email@admin2.fr",
							"admin2_lastName",
							"admin2_firstName",
							webSecurityConfig.passwordEncoder().encode("passwordAdmin2"),
							"admin"
					)
			);

			userService.saveAll(userList);
			LOGGER.info("Ajout de {} Utilisateurs", userList.size());
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
								DateTools.addDays(new Date(), - RandomTools.randomNum(100,999))
						),
						new Book(
								"title2",
								"description2",
								"shortDescription2",
								"author2",
								"editor2",
								"collection2",
								"isbn2",
								DateTools.addDays(new Date(), - RandomTools.randomNum(100,999))
						),
						new Book(
								"title3",
								"description3",
								"shortDescription3",
								"author3",
								"editor3",
								"collection3",
								"isbn3",
								DateTools.addDays(new Date(), - RandomTools.randomNum(100,999))
						),
						new Book(
								"title4",
								"description4",
								"shortDescription4",
								"author4",
								"editor4",
								"collection4",
								"isbn4",
								DateTools.addDays(new Date(), - RandomTools.randomNum(100,999))
						),
						new Book(
								"title5",
								"description5",
								"shortDescription5",
								"author5",
								"editor5",
								"collection5",
								"isbn5",
								DateTools.addDays(new Date(), - RandomTools.randomNum(100,999))
						),
						new Book(
								"title6",
								"description6",
								"shortDescription6",
								"author6",
								"editor6",
								"collection6",
								"isbn6",
								DateTools.addDays(new Date(), - RandomTools.randomNum(100,999))
						),
						new Book(
								"title7",
								"description7",
								"shortDescription7",
								"author7",
								"editor7",
								"collection7",
								"isbn7",
								DateTools.addDays(new Date(), - RandomTools.randomNum(100,999))
						),
						new Book(
								"title8",
								"description8",
								"shortDescription8",
								"author8",
								"editor8",
								"collection8",
								"isbn8",
								DateTools.addDays(new Date(), - RandomTools.randomNum(100,999))
						),
						new Book(
								"title9",
								"description9",
								"shortDescription9",
								"author9",
								"editor9",
								"collection9",
								"isbn9",
								DateTools.addDays(new Date(), - RandomTools.randomNum(100,999))
						),
						new Book(
								"title10",
								"description10",
								"shortDescription10",
								"author10",
								"editor10",
								"collection10",
								"isbn10",
								DateTools.addDays(new Date(), - RandomTools.randomNum(100,999))
						)
				);

				for (Book book : bookList) {
					book.setLoanAvailable(true);
					book.setOnline(true);
					book.setStock(RandomTools.randomNum(0,5));
				}
				bookService.saveAll(bookList);
				LOGGER.info("Ajout de {} Livres", bookList.size());
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
						),
						new BookLoan(
								userService.findUserByEmail("email@user1.fr"),
								bookService.findBookById(2L),
								appConfig.getBookLoanDuration()
						),
						new BookLoan(
								userService.findUserByEmail("email@user2.fr"),
								bookService.findBookById(1L),
								appConfig.getBookLoanDuration()
						)
				);
				bookLoanService.saveAll(bookLoanList);
				LOGGER.info("Ajout de {} prêt de livres", bookLoanList.size());

			}
		}

		if (!isBddInit) {
			LOGGER.info("Des utilisateurs existent déjà en BDD - FIN de création du jeu de données");
		} else {
			LOGGER.info("FIN de création du jeu de données");
		}
	}

}
