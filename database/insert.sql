-- Insertion des statistiques
INSERT INTO statistique(intelligence, agilite, force, endurance)
VALUES(1, 1, 1, 1);

INSERT INTO statistique(intelligence, agilite, force, endurance)
VALUES(10, 10, 10, 10);

INSERT INTO statistique(intelligence, agilite, force, endurance)
VALUES(20, 20, 20, 20);

INSERT INTO statistique(intelligence, agilite, force, endurance)
VALUES(30, 30, 30, 30);

INSERT INTO statistique(intelligence, agilite, force, endurance)
VALUES(40, 40, 40, 40);

INSERT INTO statistique(intelligence, agilite, force, endurance)
VALUES(50, 50, 50, 50);

INSERT INTO statistique(intelligence, agilite, force, endurance)
VALUES(60, 60, 60, 60);

-- Insertion des quêtes
INSERT INTO quete(nom, description, niveaurequis, datedebut, datefin, type)
VALUES('Kings recruit', 'King s Recruit is the starting quest for this game. It is a long level 1 quest and is a tutorial that introduces players to the basic concepts of the game.', 0, NULL, NULL, 'Principale');

INSERT INTO quete(nom, description, niveaurequis, datedebut, datefin, nom_quete_requise, type)
VALUES('Poisoning the Pest', 'Poisoning the Pest is a medium-length level 2 quest centered around a farm north-east of Ragni.', 1, NULL, NULL, 'Kings recruit', 'Principale');

INSERT INTO quete(nom, description, niveaurequis, datedebut, datefin, nom_quete_requise, type)
VALUES('Underwater', 'Underwater is a medium length level 8 quest started along the Coastal Trail near Maltic.', 8, NULL, NULL, NULL, 'Secondaire');

INSERT INTO quete(nom, description, niveaurequis, datedebut, datefin, nom_quete_requise, type)
VALUES('The Reaper', 'The Reaper is a special event for halloween. Get the limited character The Reaper for a limited time !', 16, '2024-10-31', '2024-11-10', NULL, 'Evenement');

INSERT INTO quete(nom, description, niveaurequis, datedebut, datefin, nom_quete_requise, type)
VALUES('Friend of zanahorias', 'Kill 40 bunnies', 0, CURRENT_DATE, CURRENT_DATE + INTERVAL '8 hours', NULL, 'Defi');

INSERT INTO quete(nom, description, niveaurequis, datedebut, datefin, nom_quete_requise, type)
VALUES('Supreme', 'Supreme is a long level 50 quest that is the culmination of the story.', 50, NULL, NULL, NULL, 'Principale');

INSERT INTO quete(nom, description, niveaurequis, datedebut, datefin, nom_quete_requise, type)
VALUES('The end', 'The end is the final quest of the game. It is a long level 100 quest that is the culmination of the story.', 100, NULL, NULL, 'Supreme', 'Principale');

-- Insertion des joueurs sans id_inventaire
INSERT INTO Joueur(nom, experience, solde, classe, id_statistique)
VALUES('Darkphoenix', 100.0, 500, 'Mage'::class, 1);

INSERT INTO Joueur(nom, experience, solde, classe, id_statistique)
VALUES('XXSoldierXX', 200.0, 1000, 'Guerrier'::class, 2);

INSERT INTO Joueur(nom, experience, solde, classe, id_statistique)
VALUES('GoulUchiha', 150.0, 750, 'Voleur'::class, 3);

INSERT INTO Joueur(nom, experience, solde, classe, id_statistique)
VALUES('EvilSoul', 300.0, 1200, 'Paladin'::class, 4);

-- Insertion des inventaires
INSERT INTO inventaire(nom_joueur)
VALUES('Darkphoenix');

INSERT INTO inventaire(nom_joueur)
VALUES('XXSoldierXX');

INSERT INTO inventaire(nom_joueur)
VALUES('GoulUchiha');

INSERT INTO inventaire(nom_joueur)
VALUES('EvilSoul');

-- Mise à jour des joueurs avec id_inventaire
UPDATE Joueur SET id_inventaire = 1 WHERE nom = 'Darkphoenix';
UPDATE Joueur SET id_inventaire = 2 WHERE nom = 'XXSoldierXX';
UPDATE Joueur SET id_inventaire = 3 WHERE nom = 'GoulUchiha';
UPDATE Joueur SET id_inventaire = 4 WHERE nom = 'EvilSoul';

-- Insertion des objets
INSERT INTO objet(nom, description, niveaurequis, nom_type, nom_rarete, id_statistique)
VALUES('Wooden Sword', 'A simple wooden sword', 0, 'Arme'::object_type, 'Commun', 1);

INSERT INTO objet(nom, description, niveaurequis, nom_type, nom_rarete, id_statistique)
VALUES('Iron Sword', 'A simple iron sword', 5, 'Arme'::object_type, 'Rare', 2);

INSERT INTO objet(nom, description, niveaurequis, nom_type, nom_rarete, id_statistique)
VALUES('Copper helmet', 'A simple copper helmet', 10, 'Armure'::object_type, 'Epique', 3);

INSERT INTO objet(nom, description, niveaurequis, nom_type, nom_rarete, id_statistique)
VALUES('Health potion', 'A simple health potion', 0, 'Consommable'::object_type, 'Commun', 4);

INSERT INTO objet(nom, description, niveaurequis, nom_type, nom_rarete, id_statistique)
VALUES('Mana potion', 'A simple mana potion', 0, 'Consommable'::object_type, 'Rare', 5);

INSERT INTO objet(nom, description, niveaurequis, nom_type, nom_rarete, id_statistique)
VALUES('Moutonator', 'The secret weapon.', 0, 'Arme'::object_type, 'Mythique', 6);

-- Insertion des slots
INSERT INTO slot(id_inventaire, id_objet)
VALUES(1, 1);

INSERT INTO slot(id_inventaire, id_objet)
VALUES(2, 2);

INSERT INTO slot(id_inventaire, id_objet)
VALUES(3, 3);

INSERT INTO slot(id_inventaire, id_objet)
VALUES(4, 4);

INSERT INTO slot(id_inventaire, id_objet)
VALUES(4, 5);

INSERT INTO slot(id_inventaire, id_objet)
VALUES(4, 6);

-- Insertion des PNJ
INSERT INTO PNJ(nom, type)
VALUES('Pigman', 'Monstre'::PNJ_type);

INSERT INTO PNJ(nom, type)
VALUES('Blacksmith', 'Marchand'::PNJ_type);

INSERT INTO PNJ(nom, type)
VALUES('Paul', 'Villageois'::PNJ_type);

INSERT INTO PNJ(nom, type)
VALUES('Louis', 'Villageois'::PNJ_type);

-- Insertion des récompenses
INSERT INTO recompense(montant, experience, id_objet, nom_quete)
VALUES(100, 50.0, 1, 'Kings recruit');

INSERT INTO recompense(montant, experience, id_objet, nom_quete)
VALUES(200, 100.0, 2, 'Poisoning the Pest');

INSERT INTO recompense(montant, experience, id_objet, nom_quete)
VALUES(300, 150.0, 3, 'Underwater');

-- Insertion des quêtes acceptées
INSERT INTO accepte(complete, nom_quete, nom_joueur)
VALUES(FALSE, 'Kings recruit', 'Darkphoenix');

INSERT INTO accepte(complete, nom_quete, nom_joueur)
VALUES(TRUE, 'Poisoning the Pest', 'XXSoldierXX');

INSERT INTO accepte(complete, nom_quete, nom_joueur)
VALUES(FALSE, 'Underwater', 'GoulUchiha');

-- Insertion des quêtes proposées par les PNJ
INSERT INTO propose(nom_quete, nom_pnj)
VALUES('Kings recruit', 'Paul');

INSERT INTO propose(nom_quete, nom_pnj)
VALUES('Poisoning the Pest', 'Paul');

INSERT INTO propose(nom_quete, nom_pnj)
VALUES('Underwater', 'Louis');