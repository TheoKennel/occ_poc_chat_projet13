Backend 
Ce projet backend est construit avec Spring Boot en respectant les principes de la Clean Architecture. Il est divisé en trois dir principaux data, domain, infrastructure.

Structure du projet
domain: Ce module ne contient aucune dépendance externe. Il définit les interfaces repository, les entités et les uses-cases.
infrastructure: Module contenant les contrôleurs, l'implémentation de la sécurité, et les requête/réponse ansin que l'a di. Il dépend de Spring Boot et interagit directement avec le côté client.
data: Implémente les interfaces repository définies dans domain et contient la logique d'accès aux données.
Prérequis
Java JDK 11 ou supérieur
Maven 3.6 ou supérieur
Base de données MySql
Configuration
Fichier .env
Créez un fichier .env à la racine du projet avec les configurations suivantes pour connecter l'application à votre base de données :

DATABASE_USERNAME=yourUsername

DATABASE_PASSWORD=yourPassword

DATABASE_URL=jdbc:mysql://localhost:3306/yourDatabase
Dépendances
Le projet utilise JWT pour la gestion des authentifications et sécurise les configurations sensibles à l'aide de Dotenv. Assurez-vous que ces dépendances sont correctement configurées dans vos fichiers pom.xml de chaque module.

Installation
# Cloner le dépôt
git clone https://github.com/TheoKennel/occ_poc_chat_projet13.git
cd back

# Installer les dépendances et construire le projet
mvn clean install
Script
Dans scriptSQL, vous trouverez les scripts sql pour la bdd.

License
Projet réalisé par Théo K.
