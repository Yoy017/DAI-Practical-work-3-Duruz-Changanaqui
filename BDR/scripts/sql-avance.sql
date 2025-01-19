-- Lister les personnages joueurs ou non-joueur.

-- SELECT * FROM Personnage
-- WHERE nom IN (
--     SELECT nom_personnage
--     FROM PNJ
-- );

SELECT nom FROM joueur
UNION
SELECT nom FROM pnj;

-- Permettre de voir le taux de joueurs d'une certaine classe

SELECT COUNT(nom) FROM Joueur
WHERE nom_classe = 'Guerrier';

SELECT COUNT(nom) FROM Joueur
WHERE nom_classe = 'Mage';

SELECT COUNT(nom) FROM Joueur
WHERE nom_classe = 'Voleur';

SELECT COUNT(nom) FROM Joueur
WHERE nom_classe = 'Paladin';

-- Connaître les statistiques avec modificateurs (buff/debuff d'objet)

SELECT * FROM Statistique
WHERE id IN (
    SELECT id_statistique
    FROM Objet
    WHERE nom = 'Copper helmet'
);

-- Savoir le niveau d'équipement moyen d'un joueur via son inventaire.

SELECT AVG(equipement) FROM (
    SELECT COUNT(id_objet) AS equipement
    FROM Inventaire
    WHERE id_inventaire = 1
    GROUP BY id_inventaire
) AS equipement;

-- Filter les objets utilisables par une classe spécifique.

SELECT * FROM Objet
WHERE nom_type IN (
    SELECT nom_type
    FROM Classe
    WHERE nom = 'Mage'
);

-- Filter les quêtes qui sont principales, secondaires ou des défis.

SELECT * FROM Quete
WHERE nom_type IN (
    SELECT nom
    FROM Type
    WHERE nom = 'Principale'
);

SELECT * FROM Quete
WHERE nom_type IN (
    SELECT nom
    FROM Type
    WHERE nom = 'Secondaire'
);

SELECT * FROM Quete
WHERE nom_type IN (
    SELECT nom
    FROM Type
    WHERE nom = 'Defi'
);

-- Permettre de savoir l'éligibilité d'un joueur à une quête (d'après son niveau/prérequis)

SELECT * FROM Quete
WHERE niveaurequis >= (
    SELECT niveau
    FROM Joueur
    WHERE nom = 'Joueur1'
);

-- Lister les quêtes acceptée par le joueur.

SELECT * FROM Quete
WHERE nom IN (
    SELECT nom_quete
    FROM Accepte
    WHERE nom_joueur = 'Joueur1'
);

-- Lister les quêtes complétés par le joueur. (association Joueur-Quête)

SELECT * FROM Quete
WHERE nom IN (
    SELECT nom_quete
    FROM Accepte
    WHERE nom_joueur = 'Joueur1'
    AND complete = TRUE
);

-- Trouver l'avancé d'un joueur dans l'histoire

SELECT * FROM Quete
WHERE nom_quete_requise IN (
    SELECT nom_quete
    FROM Accepte
    WHERE nom_joueur = 'Joueur1'
    AND complete = TRUE
);