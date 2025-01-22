# MMO Project
>Auteurs: Changanaqui Yoann & Duruz Florian

## Get Started
Suivez toutes ces étpes pour avoir notre application fonctionnel.

### Using docker

### Without docker version
1. Clonez le repository sur [github](https://github.com/Yoy017/DAI-Practical-work-3-Duruz-Changanaqui.git)
  ```sh
  git clone https://github.com/Yoy017/DAI-Practical-work-3-Duruz-Changanaqui.git
  ```

2. Importez la BDD en suivant l'ordre d'exécution des scripts suivant (les scripts se retrouvent dans /BDR/scripts/*.sql) :
  - **create_database.sql** *(éventuellement **delete.sql** si la base de données existe déjà)*
  - **insert.sql**
  - **Trigger.sql**
  - **View.sql**

3. Démarrez le serveur PostgreSQL
   ```sh
   docker compose -f postgre-sql-server.yml up
   ```
   
4. Démarrez l'application
   ```sh
   # Package l'application
   ./mvnw package
   
   # Exécute l'application
   java -jar target/MMO_Project-1.0-SNAPSHOT.jar
   ```
![image](https://github.com/user-attachments/assets/630061c3-cb33-4cc9-9c09-48b3ccfd26f3)

Maintenant l'application tourne ainsi que le serveur PostgreSQL. Vous pouvez dorénavant accéder à notre application depuis [localhost:8080](localhost:8080).

## Structure des Fichiers
L'application possède la structure suivante en suivant le modèle MVC pour la gestion avec la BDD.
![image](https://github.com/user-attachments/assets/d7d68c56-8aa4-49ba-bde7-1f0792efd261)

### Controller
- Sert à gérer la logique entre les interactions de l'utilisateur (via l'interface ou des commandes) et les actions associées au modèle.

### Database
- Contient les classes pour la connexion et les interactions avec la base de données PostgreSQL.

### Model
- Représente les données et leur logique métier.

#### Entity
- Définit les classes de données (entités) correspondant aux tables de la base de données.

#### Repository
- Fournit des méthodes pour accéder et manipuler les entités stockées dans la base de données.

Le fichier **Dockerfile** définit l'image Docker utilisée pour exécuter l'application. Déclare la commande pour lancer le programme Java.

Le fichier **docker-compose.yml** configure le service PostgreSQL utilisé pour la base de données, en définissant les variables d'environnement, les ports d'accès, et le réseau Docker pour permettre la communication entre les services.

# Application protocol interface _(API)_
Vous pouvez retrouvez la description de notre API dans le fichier [api.md](./api.md).

# Virtual machine
Configurons la machine virtuelle.

1.

# DNS
Configurons notre DNS zone pour pouvoir accéder à l'application web.

1.

# Examples using curl
...
