# TP12 — API REST sécurisée avec JWT

Dans ce TP, Spring Security vérifie les identifiants de l’utilisateur enregistré dans MySQL. Si la connexion réussit, l’API renvoie un jeton JWT. Ce jeton permet d’accéder aux routes protégées.

![Génération du token JWT](./Screenshot%202026-11-09%20112725.png)

Dans Postman, nous envoyons les identifiants du compte `admin` à `POST /api/auth/login`. L’API répond `200 OK` et renvoie le jeton dans le champ `token`.

Nous copions ce jeton dans **Authorization → Bearer Token** et envoyons `GET /api/user/profile`. L’API répond `200 OK` et affiche le nom de l’utilisateur connecté, `admin`.

![Accès au profil protégé dans Postman](./Screenshot%202026-11-09%20112949.png)
