-- TRIGGERS

-- Trigger to update experience in Joueur when a Recompense is added
CREATE OR REPLACE FUNCTION update_joueur_experience()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE Joueur
    SET experience = experience + NEW.experience
    WHERE nom = (SELECT nom FROM Inventaire WHERE id = (SELECT id_inventaire FROM Slot WHERE id_objet = NEW.id_objet));
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_joueur_experience
AFTER INSERT ON Recompense
FOR EACH ROW
EXECUTE FUNCTION update_joueur_experience();

-- Trigger to update solde in Joueur when a new Recompense is added
CREATE OR REPLACE FUNCTION update_joueur_solde()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE Joueur
    SET solde = solde + NEW.montant
    WHERE nom = (SELECT nom FROM Inventaire WHERE id = (SELECT id_inventaire FROM Slot WHERE id_objet = NEW.id_objet));
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_joueur_solde
AFTER INSERT ON Recompense
FOR EACH ROW
EXECUTE FUNCTION update_joueur_solde();

-- Trigger to update nom_quete in Joueur when a Quete is deleted
CREATE OR REPLACE FUNCTION update_joueur_on_quete_delete()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE Joueur
    SET nom_quete = NULL
    WHERE nom_quete = OLD.nom;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_joueur_on_quete_delete
BEFORE DELETE ON Quete
FOR EACH ROW
EXECUTE FUNCTION update_joueur_on_quete_delete();

-- Trigger to update nom_quete in Joueur when a Quete is updated
CREATE OR REPLACE FUNCTION update_joueur_on_quete_update()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE Joueur
    SET nom_quete = NEW.nom
    WHERE nom_quete = OLD.nom;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_joueur_on_quete_update
AFTER UPDATE ON Quete
FOR EACH ROW
EXECUTE FUNCTION update_joueur_on_quete_update();

-- Trigger to update nom_quete in Joueur when a Quete is inserted
CREATE OR REPLACE FUNCTION update_joueur_on_quete_insert()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE Joueur
    SET nom_quete = NEW.nom
    WHERE nom = NEW.nom_joueur;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_joueur_on_quete_insert
AFTER INSERT ON Accepte
FOR EACH ROW
EXECUTE FUNCTION update_joueur_on_quete_insert();

-- Trigger to update the statistics of a player when an object is updated in his inventory OK
create function update_player_statistic() returns trigger
    language plpgsql
as
$$
BEGIN
    UPDATE statistique
    SET intelligence = (SELECT SUM(s.intelligence)
                        FROM Slot sl
                        JOIN Objet o ON sl.id_objet = o.id
                        JOIN Statistique s ON o.id_statistique = s.id
                        WHERE sl.id_inventaire = NEW.id_inventaire AND sl.type = 'Equipement'),
        agilite = (SELECT SUM(s.agilite)
                   FROM Slot sl
                   JOIN Objet o ON sl.id_objet = o.id
                   JOIN Statistique s ON o.id_statistique = s.id
                   WHERE sl.id_inventaire = NEW.id_inventaire AND sl.type = 'Equipement'),
        force = (SELECT SUM(s.force)
                 FROM Slot sl
                 JOIN Objet o ON sl.id_objet = o.id
                 JOIN Statistique s ON o.id_statistique = s.id
                 WHERE sl.id_inventaire = NEW.id_inventaire AND sl.type = 'Equipement'),
        endurance = (SELECT SUM(s.endurance)
                     FROM Slot sl
                     JOIN Objet o ON sl.id_objet = o.id
                     JOIN Statistique s ON o.id_statistique = s.id
                     WHERE sl.id_inventaire = NEW.id_inventaire AND sl.type = 'Equipement')
    WHERE id = NEW.id_inventaire;
    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_update_player_statistic
AFTER UPDATE ON Slot
FOR EACH ROW
EXECUTE FUNCTION update_player_statistic();

-- Trigger to automatically create an inventory for a new player
CREATE OR REPLACE FUNCTION create_inventory_for_new_player() RETURNS TRIGGER AS $$
BEGIN
          INSERT INTO inventaire(nom_joueur) VALUES (NEW.nom);
          RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER after_joueur_insert
AFTER INSERT ON Joueur
FOR EACH ROW
EXECUTE FUNCTION create_inventory_for_new_player();

-- Get the final statistics of a player with the sum of all the statistics of the objects in his inventory
CREATE OR REPLACE FUNCTION get_final_statistics()
RETURNS TABLE(intelligence INT, agilite INT, force INT, endurance INT) AS $$
BEGIN
    RETURN QUERY
    SELECT SUM(s.intelligence), SUM(s.agilite), SUM(s.force), SUM(s.endurance)
    FROM Joueur j
    JOIN Inventaire i ON j.id_inventaire = i.id
    JOIN Slot sl ON i.id = sl.id_inventaire
    JOIN Objet o ON sl.id_objet = o.id
    JOIN Statistique s ON o.id_statistique = s.id
    WHERE j.nom = NEW.nom;
END;
$$ LANGUAGE plpgsql;