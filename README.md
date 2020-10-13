# Bibliothèque en ligne myLibrary [OCP7]
Développez le nouveau système d’information de la bibliothèque d’une grande ville

## Conception d'une application web et de micro-services 

### Objet : 

Nous souhaitons confier à la DSI la création d’un ensemble d’outils numériques pour les différents acteurs des 
bibliothèques de la ville. 

En voici la liste :

Un site web et une application mobile pour les usagers de la bibliothèque.
Un logiciel pour le personnel des bibliothèques.
Un logiciel pour des traitements automatisés (mails de relance).
Le site web permettra aux usagers de suivre les prêts de leurs ouvrages. Il pourront :

Rechercher des ouvrages et voir le nombre d’exemplaires disponibles.
Consulter leurs prêts en cours. Les prêts sont pour une période de 4 semaines.
Prolonger un prêt en cours. Le prêt d’un ouvrage n’est prolongeable qu’une seule fois. La prolongation ajoute une 
nouvelle période de prêt (4 semaines) à la période initiale.
Nous attendons également une application mobile iOS et Android qui fournira les mêmes services que le site web.

Le logiciel pour le personnel des bibliothèques permettra notamment de gérer les emprunts et les livres rendus.

Le logiciel pour les traitements automatisés, que tu as désigné par le terme “batch” il me semble, 
permettra d’envoyer des mails de relance aux usagers n’ayant pas rendu les livres en fin de période de prêt. 
L’envoi est automatique à la fréquence d’un par jour.


### Contexte :

Vous travaillez au sein de la Direction du Système d’Information (DSI) de la mairie d’une grande ville, 
sous la direction de la responsable du service. 
La DSI est en charge de tous les traitements numériques pour la mairie et les structures qui lui sont rattachées, 
comme la gestion du site web de la ville par exemple.
Vous êtes missionné pour la réalisation des premiers produits !

#### Travail demandé : Release 1.0
- API Web Rest.
- Application web framework MVC.
- Batch de relance par email. 

### Livrables attendus :
* Le code source des composants à développer de la Release 1.
* Les scripts SQL de création de la base de données avec un jeu de données de démonstration.
* Une documentation succincte expliquant comment déployer l'application.

## Installation
~ En cours de rédaction ~

## Déploiement
~ En cours de rédaction ~

## Réalisé avec

* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - IDE (JDK11)
* [Maven](https://maven.apache.org/) - Pour la gestion des dépendances du projet
* [SpringBoot v2.3.2](https://spring.io/projects/spring-boot) - Framework (+Spring DATA/JPA/HIBERNATE & Spring SECURITY) 
* [Thymeleaf](https://www.thymeleaf.org/) - Moteur de templating Java
* [Bootstrap 4](https://getbootstrap.com/) - framework de design responsive (Librairies HTML, CSS et JS)
* [MySQL WorkBench](https://www.mysql.com/) - SGB MySQL, pour la conception du Modèle Physique de Donnée
* [DBeaver](https://dbeaver.io/) - SGBD universelle, pour l'écriture des scripts SQL et des tests MySQL
* [WampServer](http://www.wampserver.com/) - Gestion de serveurs Apache, PHP, MySQL (+PHP MyAdmin)

## Auteurs

* **Rémy VALLET** - *Initial work* - [rvallet](https://github.com/rvallet)

<!-- Voir également la liste des [contributeurs](https://github.com/rvallet/ocp7-library/graphs/contributors) qui ont participés au projet. -->

## License
This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/rvallet/ocp7-library/blob/feature-user/LICENSE) file for details
