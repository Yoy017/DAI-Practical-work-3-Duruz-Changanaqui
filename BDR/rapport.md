# Rapport - Phase 1
>Auteur: Changanaqui Yoann & Duruz Florian

## Introduction
Notre projet est destiné à un développeur de jeu vidéo. Plus précisement pour un jeu de type MMO RPG.
Il donnera la possibilité de stocker les informations typique de ce jeu. En incarnant une des différentes classes proposées il évolue dans
un monde riche. Avec des quêtes pour progresser. La base de données sera utilisée pour gérer les informations liées aux joueurs, quêtes, événements, objets et autres éléments essentiels du jeu.

## Objectifs
L'objectif principal est de créer une base de données qui permet :

Le projet se concentre sur un univers de jeu où le joueur est libre de choisir parmi plusieurs classes et de progresser à son rythme. Le joueur doit accomplir des quêtes, dont certaines sont essentielles à la progression de l’histoire, tandis que d’autres sont optionnelles. Les quêtes principales suivent un ordre strict, tandis que les quêtes secondaires peuvent être entreprises sans restrictions de niveau.

La base de données devra gérer une variété de données, telles que les informations du joueur, les objets qu'il possède, ses interactions avec le marché, et sa progression dans les quêtes et événements.

## Besoins en données
Les entités principales à inclure dans la base de données sont :

`Personnage` : Contient les informations relatives à chaque personnage, dont son nom, son niveau etc.

`Joueur` : Le joueur possédant un solde, une classe, etc.

`PNJ` : Les personnage non joueur.

          Marchand
          Villageois
          Monstres

`Classe` : Une classe de personnage (guerrier, mage, archer, etc.)

`Statistiques` : Les points de compétences du joueur. Force, vitesse, resistance, resistance magique, etc.

`Inventaire` : Les objets qui appartiennent au joueur.

          Slot casque
          Slot buste
          Slot jambière
          Slot botte
          Slot divers

`Objet` : Représente les objets disponible en jeu. Chaque objet a un nom, une description, par qui est utilisable l'objet et un niveau de rareté.

`Type` : Le type de l'objet (Casque, épée, nourriture, etc.)

`Rareté` : Niveau de rareté d'un objet. (Commun, rare, légendaire, etc.)

`Quête` : Les quêtes sont des missions que le joueur peut accomplir pour progresser dans le jeu. Chaque quête a un nom, une description, un niveau minimum recommandé et des récompenses associées. *(argent et équipement)*

`Quête principales`

`Quête secondaires` 

`Événement` : Des événements ponctuels qui permettent aux joueurs de gagner des récompenses supplémentaires. Les événements peuvent inclure des défis, des tournois, ou des événements saisonniers.

`Défi` : Design assez pauvre du jeu vidéo.

          Quotidient
          Hebdomadaire
          Mensuel

## Fonctionnalités
- Lister les personnages joueurs ou non-joueur.
- Permettre de voir le taux de joueurs d'une certaine classe
- Connaître les statistiques avec modificateurs (buff/debuff d'objet)
- Savoir le niveau d'équipement moyen d'un joueur via son inventaire.

- Filter les objets utilisables par une classe spécifique.
- Lister l'inventaire d'un joueur.

- Filter les quêtes qui sont principales, secondaires ou des défis.
- Permettre de savoir l'éligibilité d'un joueur à une quête *(d'après son niveau/prérequis)*
- Lister les quêtes acceptée par le joueur.
- Lister les quêtes complétés par le joueur. *(association Joueur-Quête)*
- Trouver l'avancé d'un joueur dans l'histoire