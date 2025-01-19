# Utilise une image de base Java 21
FROM openjdk:21-jdk-slim

# Répertoire de travail pour l'application
WORKDIR /app

# Copier le fichier JAR dans le conteneur
COPY target/MMO_Project-1.0-SNAPSHOT.jar /app/MMO_Project-1.0-SNAPSHOT.jar

# Commande pour exécuter le programme Java
ENTRYPOINT ["java", "-jar", "/app/MMO_Project-1.0-SNAPSHOT.jar"]
