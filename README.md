# customer-onboarding

## Environnement
### front :  
    - angular v16
    - node v18.20.1

### back (microservice):
    - java jdk 21
    - mysql v8

## branche git :
Si vous voulez cloner ou télécharger le dépot alors utiliser la branche "main"

## Indication sur le lancement des services
Il faudra suivre l'ordre suivant pour le lancement des service:
    - configserver 
    - eurekaserver
    - submission-service
    - datareference-service
    - gatewayserver

## Indication sur le lancement de la partie front
L'application pour l'interface utilisateur se trouve dans le dossier "onboarding-app"

## Déroulement de test
- Pour le persona CUSTOMER:
En accèdant à l'url http://localhost:4200, le client peut acceder à la formulaire de soumission

- Pour le persona PROCESSOR:
    . Ce persona doit s'authentifier en accedant à la page de login "http://localhost:4200/login"
    . Pour faciliter le test un compte par défaut a déjà été créé
    . Après l'authentification, il accède à la dashboard (liste des soumission avec possibilité d'action "reject" ou "poceed" ou "edit")

- Pour le persona APPOVER:
    . Ce persona doit s'authentifier en accedant à la page de login "http://localhost:4200/login"
    . Pour faciliter le test un compte par défaut a déjà été créé (email=approver@gmail.com, password=password)
    . Après l'authentification, il accède à la liste des soumission traité par le persona PROCESSOR  avec possibilité d'action "reject" ou "approve"

## Proposition d'amélioration
### microservice
- Historique des actions effectuées (dans la bd)
- Utilisation d'un système de caching
- Gestion centralisé des logs
- Amélioration de la sécurité des services
- Amélioration du suivi et monitoring des services
- Utilisation de docker pour conteneuriser les services 

### front
- Ajout de pop up pour valider les actions comme "reject" ou "approve"
- Pagination et recherche multicritère sur la page de liste
- Navigation entre les pages


## NB
- J'ai fait du tdd mais uniquement sur certaine partie du developpement
- Les api pour la mise en place des fonctionnalités requises ont toutes été developpés
mais malheureusement je n'ai pas pu tous les intégrés à la partie front