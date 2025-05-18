# Rapport du Projet E-Banking Backend

## Description du Projet
Ce projet est une application backend pour un système de banque électronique développé avec Spring Boot. Il fournit des API RESTful pour gérer les comptes bancaires, les clients et les opérations bancaires.

## Architecture
L'application suit une architecture en couches:
- **Contrôleurs**: Gèrent les requêtes HTTP et les réponses
- **Services**: Contiennent la logique métier
- **Repositories**: Accèdent à la base de données
- **Entités**: Représentent les tables de la base de données
- **DTOs**: Objets de transfert de données entre le client et le serveur

## Fonctionnalités
- Authentification et autorisation avec JWT
- Gestion des clients
- Gestion des comptes bancaires
- Opérations bancaires (dépôt, retrait, virement)
- Consultation des historiques des opérations

## Configuration de Sécurité
L'application utilise Spring Security avec JWT pour l'authentification:
- Les utilisateurs peuvent se connecter via l'endpoint `/auth/login`
- Un token JWT est généré avec une durée de validité de 10 minutes
- Les endpoints sont sécurisés selon les rôles des utilisateurs

## Base de données
- Base de données MySQL
- Configuration dans `application.properties`
- Création automatique des tables au démarrage

## Points d'API
### Authentification
- POST `/auth/login`: Authentification et génération de token JWT

### Clients
- GET `/customers`: Liste tous les clients
- GET `/customers/{id}`: Récupère un client par ID
- POST `/customers`: Crée un nouveau client
- PUT `/customers/{id}`: Met à jour un client existant
- DELETE `/customers/{id}`: Supprime un client

### Comptes
- GET `/accounts`: Liste tous les comptes
- GET `/accounts/{id}`: Récupère un compte par ID
- GET `/accounts/{id}/operations`: Liste les opérations d'un compte
- POST `/accounts/debit`: Effectue un retrait
- POST `/accounts/credit`: Effectue un dépôt
- POST `/accounts/transfer`: Effectue un virement

## Problèmes connus
- Dans la réponse d'authentification, la clé du token JWT est `access-token`.

## Comment démarrer
1. Assurez-vous que MySQL est installé et en cours d'exécution
2. Configurez les paramètres de la base de données dans `application.properties`
3. Exécutez l'application avec `mvn spring-boot:run`
4. L'application sera accessible à l'adresse `http://localhost:8080` 