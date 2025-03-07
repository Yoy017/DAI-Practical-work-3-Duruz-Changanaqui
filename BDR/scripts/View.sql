-- VIEWS
SET SEARCH_PATH = mmo_game;

-- Vue pour lister toutes les statistiques des joueurs OK
CREATE VIEW vw_joueurs_statistiques AS
SELECT j.nom AS nom_joueur, s.* FROM Joueur j
    INNER JOIN statistique s
    ON j.id_statistique = s.id;

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

-- Vue pour lister tous les joueurs avec leurs inventaires et les objets qu'ils possèdent OK
CREATE OR REPLACE VIEW vw_joueurs_inventaire AS
SELECT joueur.nom AS nom_joueur, o.*, s.type AS type_slot FROM slot AS s
                INNER JOIN inventaire
                ON s.id_inventaire = inventaire.id
                INNER JOIN joueur
                ON inventaire.id = joueur.id_inventaire
                INNER JOIN objet AS o
                ON s.id_objet = o.id
                INNER JOIN statistique
                ON o.id_statistique = statistique.id;

-- Vue pour lister les objets que le joueur ne possède pas OK
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

-- Classement des joueurs détérminé par leurs expériences OK
CREATE OR REPLACE VIEW vw_joueur_classement AS
SELECT joueur.*
FROM Joueur
    INNER JOIN statistique
    ON joueur.id_statistique = statistique.id
GROUP BY joueur.nom, experience
ORDER BY experience DESC;

-- Lister les personnages joueurs ou non-joueur
CREATE OR REPLACE VIEW vw_personnages AS
SELECT 'Joueur' AS type_personnage, nom, NULL AS type_pnj
FROM Joueur
UNION ALL
SELECT 'PNJ' AS type_personnage, nom, type AS type_pnj
FROM PNJ;

-- Permettre de voir le taux de joueurs d'une certaine classe
CREATE OR REPLACE VIEW vw_taux_joueurs_par_classe AS
SELECT classe, COUNT(*) AS nombre_joueurs,
       ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM Joueur), 2) AS pourcentage
FROM Joueur
GROUP BY classe;

--Trouver l'avancée de tous les joueurs dans l'histoire (basée sur les quêtes principales complétées)
CREATE OR REPLACE VIEW vw_progression_histoire_joueurs AS
SELECT 
    j.nom AS joueur,
    COALESCE(COUNT(q.nom), 0) AS quetes_principales_completees,
    (SELECT COUNT(*) FROM Quete WHERE type = 'Principale') AS quetes_principales_totales,
    COALESCE(ROUND(COUNT(q.nom)::DECIMAL / NULLIF((SELECT COUNT(*) FROM Quete WHERE type = 'Principale'), 0) * 100, 2),0) AS progression_pourcentage
FROM Joueur j
LEFT JOIN Accepte a ON j.nom = a.nom_joueur AND a.complete = TRUE
LEFT JOIN Quete q ON a.nom_quete = q.nom AND q.type = 'Principale' -- Filtre sur les quêtes principales
GROUP BY j.nom;