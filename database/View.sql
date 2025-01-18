-- VIEWS

-- Vue pour lister un joueur avec ses statistiques
CREATE VIEW vw_joueur_statistique AS
SELECT j.nom, j.experience, j.solde, j.classe, s.intelligence, s.agilite, s.force, s.endurance
FROM Joueur j
JOIN Statistique s ON j.id_statistique = s.id;

-- Vue pour lister toutes les quêtes avec les joueurs qui les ont acceptées et leurs récompenses
CREATE VIEW vw_quete_joueur_recompense AS
SELECT q.nom AS nom_quete, q.description, q.niveauRequis, j.nom AS nom_joueur, r.montant, r.experience
FROM Quete q
    JOIN Accepte a ON q.nom = a.nom_quete
    JOIN Joueur j ON a.nom_joueur = j.nom
    JOIN Recompense r ON q.nom = r.nom_quete;

-- Vue pour lister tous les objets avec leurs types, raretés et statistiques
CREATE VIEW vw_objet_statistique AS
SELECT o.nom AS nom_objet, o.description, o.niveauRequis, o.nom_type, o.nom_rarete, s.intelligence, s.agilite, s.force, s.endurance
FROM Objet o
JOIN Statistique s ON o.id_statistique = s.id;

-- Vue pour lister tous les joueurs avec leurs quêtes acceptées et les récompenses obtenues
CREATE VIEW vw_joueur_quete_recompense AS
SELECT j.nom AS nom_joueur, q.nom AS nom_quete, q.description, q.niveauRequis, r.montant, r.experience
FROM Joueur j
JOIN Accepte a ON j.nom = a.nom_joueur
JOIN Quete q ON a.nom_quete = q.nom
JOIN Recompense r ON q.nom = r.nom_quete;

-- Vue pour lister tous les joueurs avec leurs inventaires et les objets qu'ils possèdent
CREATE VIEW vw_joueurs_inventaire AS
SELECT j.nom AS nom_joueur, i.id AS id_inventaire, sl.type AS type_slot, o.nom AS nom_objet, o.description, o.niveauRequis, o.nom_type, o.nom_rarete, s.intelligence, s.agilite, s.force, s.endurance
FROM Joueur j
JOIN Inventaire i ON j.id_inventaire = i.id
JOIN Slot sl ON i.id = sl.id_inventaire
JOIN Objet o ON sl.id_objet = o.id
JOIN Statistique s ON o.id_statistique = s.id;

-- Vue pour lister les objets que le joueur ne possède pas
CREATE OR REPLACE VIEW vw_objets_non_possedes AS
SELECT j.nom AS nom_joueur, o.id AS id_objet, o.nom AS nom_objet, o.description, o.niveauRequis, o.nom_type, o.nom_rarete, s.intelligence, s.agilite, s.force, s.endurance
FROM Joueur j
CROSS JOIN Objet o
LEFT JOIN (
    SELECT i.nom_joueur, sl.id_objet
    FROM Inventaire i
    JOIN Slot sl ON i.id = sl.id_inventaire
) possede ON j.nom = possede.nom_joueur AND o.id = possede.id_objet
LEFT JOIN Statistique s ON o.id_statistique = s.id
WHERE possede.id_objet IS NULL;

-- Classement des joueurs détérminé par leurs expériences
CREATE OR REPLACE VIEW vw_joueur_classement AS
SELECT joueur.*
FROM joueur
    INNER JOIN statistique
    ON joueur.id_statistique = statistique.id
GROUP BY joueur.nom, experience
ORDER BY experience DESC;


-- Vue pour lister tous les PNJ avec les quêtes qu'ils proposent
-- CREATE VIEW vw_pnj_quete AS
-- SELECT p.nom AS nom_pnj, q.nom AS nom_quete, q.description, q.niveauRequis, q.dateDebut, q.dateFin, q.type
-- FROM PNJ p
-- JOIN Propose pr ON p.nom = pr.nom_pnj
-- JOIN Quete q ON pr.nom_quete = q.nom;