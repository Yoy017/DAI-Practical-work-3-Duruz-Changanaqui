DROP SCHEMA IF EXISTS mmo_game CASCADE;

CREATE SCHEMA mmo_game;

SET SEARCH_PATH = mmo_game;

--Modification pour Inventaire
CREATE TYPE SLOT_TYPE AS ENUM (
    'Bag',
    'Equipement'
);
ALTER TYPE SLOT_TYPE OWNER TO bdr;

-- New enum type for quests
CREATE TYPE quest_type AS ENUM(
    'Principale',
    'Secondaire',
    'Evenement',
    'Defi'
);

CREATE TYPE PNJ_type AS ENUM(
    'Monstre',
    'Marchand',
    'Villageois'
);

-- New enum type for object rarity
CREATE TYPE rarete AS ENUM(
    'Commun',
    'Rare',
    'Epique',
    'Legendaire',
    'Mythique'
);

-- New enum type for object type
CREATE TYPE object_type AS ENUM(
    'Arme',
    'Armure',
    'Consommable',
    'Ressource',
    'Divers'
);

-- New enum type for class of player
CREATE TYPE class AS ENUM(
    'Guerrier',
    'Mage',
    'Voleur',
    'Paladin',
    'Chasseur',
    'Druide',
    'Prêtre',
    'Chaman',
    'Demoniste',
    'Moine',
    'Chevalier de la mort',
    'Chasseur de démon'
);

CREATE TABLE Joueur(
    nom VARCHAR(255) PRIMARY KEY,
    experience DECIMAL(10, 3),
    solde INT,
    classe class,

    id_inventaire INT,
    id_statistique INT 
);

CREATE TABLE PNJ(
    nom VARCHAR(255) PRIMARY KEY,
    type PNJ_type
);

CREATE TABLE Statistique(
    id SERIAL PRIMARY KEY,
    intelligence INT,
    agilite INT,
    force INT,
    endurance INT
);

CREATE TABLE Objet(
    id SERIAL PRIMARY KEY, -- On pourrait enlever l'id et mettre nom en primary key, la sécurité n'est pas notre priorité pour ce cours.
    nom VARCHAR(255),
    description VARCHAR(255),
    niveauRequis DECIMAL(10, 3),
    
    nom_type object_type,
    nom_rarete rarete,
    id_statistique INT
);

--Modification
CREATE TABLE Inventaire(
    id SERIAL PRIMARY KEY,

	nom_joueur VARCHAR(255)
);

CREATE TABLE Slot(
    id SERIAL PRIMARY KEY,
    type SLOT_TYPE DEFAULT 'Bag'::SLOT_TYPE,

    id_inventaire INT NOT NULL,
    id_objet INT
);

-- Adding an enum type
CREATE TABLE Quete(
    nom VARCHAR(255) PRIMARY KEY,
    description VARCHAR(255),
    niveauRequis DECIMAL(10, 3),
    dateDebut TIMESTAMP,
    dateFin TIMESTAMP,
    type quest_type,

    nom_quete_requise VARCHAR(255)
);

CREATE TABLE Recompense(
    montant INT,
    experience DECIMAL(10, 3),

    id_objet INT,
    nom_quete VARCHAR(255),
    PRIMARY KEY (id_objet, nom_quete)
);

CREATE TABLE Accepte(
    complete BOOLEAN,

    nom_quete VARCHAR(255),
    nom_joueur VARCHAR(255),
    PRIMARY KEY (nom_quete, nom_joueur)
);

CREATE TABLE Propose(
    nom_quete VARCHAR(255),
    nom_pnj VARCHAR(255),
    PRIMARY KEY (nom_quete, nom_pnj)
);

-- Add foreign keys

-- Joueur
ALTER TABLE Joueur
ADD CONSTRAINT FK_Joueur_Statistique FOREIGN KEY (id_statistique) REFERENCES Statistique(id) ON DELETE CASCADE;

ALTER TABLE Joueur
ADD CONSTRAINT FK_Joueur_Inventaire FOREIGN KEY (id_inventaire) REFERENCES Inventaire(id) ON DELETE CASCADE;

ALTER TABLE Accepte
ADD CONSTRAINT FK_Accepte_Quete FOREIGN KEY (nom_quete) REFERENCES Quete(nom);

ALTER TABLE Accepte
ADD CONSTRAINT FK_Accepte_Joueur FOREIGN KEY (nom_joueur) REFERENCES Joueur(nom);

ALTER TABLE Propose
ADD CONSTRAINT FK_Propose_Quete FOREIGN KEY (nom_pnj) REFERENCES PNJ(nom);

ALTER TABLE Propose
ADD CONSTRAINT FK_Propose_PNJ FOREIGN KEY (nom_quete) REFERENCES Quete(nom);

-- Inventaire
ALTER TABLE Inventaire
ADD CONSTRAINT FK_Inventaire_Joueur FOREIGN KEY (nom_joueur) REFERENCES Joueur(nom) ON DELETE CASCADE;

ALTER TABLE Slot
ADD CONSTRAINT FK_Slot_Inventaire FOREIGN KEY (id_inventaire) REFERENCES Inventaire(id) ON DELETE CASCADE;

ALTER TABLE Slot
ADD CONSTRAINT FK_Slot_Objet FOREIGN KEY (id_objet) REFERENCES Objet(id);

-- Objet
ALTER TABLE Objet
ADD CONSTRAINT FK_Objet_Statistique FOREIGN KEY (id_statistique) REFERENCES Statistique(id);

-- Recompense
ALTER TABLE Recompense
ADD CONSTRAINT FK_Recompense_Objet FOREIGN KEY (id_objet) REFERENCES Objet(id);

ALTER TABLE Recompense
ADD CONSTRAINT FK_Recompense_Quete FOREIGN KEY (nom_quete) REFERENCES Quete(nom);

-- Quete
ALTER TABLE Quete
ADD CONSTRAINT FK_Quete_Require FOREIGN KEY (nom_quete_requise) REFERENCES Quete(nom);