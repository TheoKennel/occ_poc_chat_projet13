# Backend

Ce projet backend est construit avec Spring Boot en respectant les principes de la Clean Architecture. Il est divisé en trois répertoires principaux : `data`, `domain`, et `infrastructure`.

## Structure du projet

- **domain** : Ce module ne contient aucune dépendance externe. Il définit les interfaces repository, les entités et les use-cases.
- **infrastructure** : Ce module contient les contrôleurs, l'implémentation de la sécurité, et les requêtes/réponses ainsi que l'accès aux données. Il dépend de Spring Boot et interagit directement avec le côté client.
- **data** : Ce module implémente les interfaces repository définies dans `domain` et contient la logique d'accès aux données.

## Prérequis

- **Java** : JDK 17
- **Maven** : 3.6 ou supérieur
- **Base de données** : MySQL

## Configuration

Créez un fichier `.env` à la racine du projet avec les configurations suivantes pour connecter l'application à votre base de données :

```env
DATABASE_USERNAME=yourUsername
DATABASE_PASSWORD=yourPassword
DATABASE_URL=jdbc:mysql://localhost:3306/yourDatabase
```

## Installation

1. Cloner le dépôt :
    ```bash
    git clone https://github.com/TheoKennel/occ_poc_chat_projet13.git
    cd api
    ```

2. Installer les dépendances et construire le projet :
    ```bash
    mvn clean install
    ```

## Script

Dans le dossier `scriptSQL`, vous trouverez les scripts SQL pour la base de données.

## License

Projet réalisé par Théo K.
