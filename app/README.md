# Frontend

![Angular Logo](https://angular.io/assets/images/logos/angular/angular.png)

## Prérequis

Ce projet nécessite **Node.js** et **npm**. Assurez-vous qu'ils sont installés sur votre pc.

## Installation

Pour installer et démarrer le projet, suivez les étapes ci-dessous :

1. Cloner le dépôt :
    ```bash
    git clone https://github.com/TheoKennel/Developpez-une-application-full-stack-complete.git
    cd front
    ```

2. Installer les dépendances :
    ```bash
    npm install
    ```

## Utilisation

1. Démarrer le front :
    ```bash
    npm start
    ```

## Architecture

Le projet suit une architecture en **component**. Il utilise un **local storage** ainsi qu'un **interceptor** pour les JWT / refresh token.
Pour le chat, il communique via websocket en tant que client et le api en tant que serveur.
