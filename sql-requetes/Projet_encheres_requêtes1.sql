-- Suppression de colonnes--

DROP TABLE RETRAITS;

DROP TABLE ENCHERES;

DROP TABLE ARTICLES_VENDUS

DROP TABLE UTILISATEURS;

DROP TABLE CATEGORIES;

-- créer une table role pour définir qui est admin ou pas--

CREATE TABLE ROLES(
ROLE NVARCHAR(50) NOT NULL,
IS_ADMIN int NOT NULL ,
PRIMARY KEY ([ROLE],[IS_ADMIN]));
INSERT INTO [ROLES] ([ROLE],[IS_ADMIN]) VALUES ('ROLE_MEMBRE',0);
INSERT INTO [ROLES] ([ROLE],[IS_ADMIN]) VALUES ('ROLE_MEMBRE',1);
INSERT INTO [ROLES] ([ROLE],[IS_ADMIN]) VALUES ('ROLE_ADMIN',1);




-- Email unique pour ne pas avoir plusieurs utilisateurs identiques--
ALTER TABLE UTILISATEURS ADD CONSTRAINT UN_mot_de_passe UNIQUE (email);




-- Insertion de données dans utilisateurs--

INSERT INTO Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES
    ('jdoe', 'Doe', 'John', 'john.doe@example.com', '0123456789', '10 rue de Paris', '75001', 'Paris', '{bcrypt}$2y$10$uQME.1FDRC4f3MTiQmv.WuWA6KjiPBPT1IBgF.qND5sFCrgA58x0m', 100.00, 0)/*Mot de Passe = password123*/,
    ('asmith', 'Smith', 'Anna', 'anna.smith@example.com', '0123456790', '20 avenue des Champs', '75008', 'Paris', '{bcrypt}$2y$10$yRjXjdryx6UjQR/jIyirceQubr04y0S..rkTe0puJilc155hdtxjm', 250.50, 0)/*Mot de Passe = securePass1*/,
    ('jdupont', 'Dupont', 'Jean', 'jean.dupont@example.com', '0654321987', '5 place de l''Étoile', '69001', 'Lyon', '{bcrypt}$2y$10$fdT.k1LH.NACI4etrLfSJecXKUz3LDL6UpmGTXeUfNylsqE.fLGeG', 300.00, 1)/*Mot de Passe = jeanPass2024*/,
    ('claporte', 'Laporte', 'Claire', 'claire.laporte@example.com', '0678192345', '12 boulevard Haussmann', '75009', 'Paris', '{bcrypt}$2y$10$nPs7xuVP2p.IP2Ldcd7TNuS0upGhx2JsRGVicPiRn3QZgFPAiKJMq', 500.00, 1)/*Mot de Passe = claireAdmin*/,
    ('mpaul', 'Paul', 'Martin', 'martin.paul@example.com', '0612345678', '8 rue Victor Hugo', '33000', 'Bordeaux', '{bcrypt}$2y$10$V.dUk7yvAfSiXLAKxlzWpeN2IX4flXzCEh3bBkWjyrUvq4UMar4o6', 150.00, 0)/*Mot de Passe = martin123*/,
    ('elou', 'Lou', 'Emma', 'emma.lou@example.com', '0698123456', '3 chemin des Fleurs', '13000', 'Marseille', '{bcrypt}$2y$10$wWEXBHXpST6oA6OVJekrn.Ii92DvOZ3fZ/3UJAeyz.j.WH9FhaxWe!', 200.00, 0)/*Mot de Passe = emma2023!*/,
    ('kroger', 'Rogers', 'Kate', 'kate.rogers@example.com', '0601234567', '15 rue Saint-Jacques', '67000', 'Strasbourg', '{bcrypt}$2y$10$H4Xvh8hMoPfVkUFGfU1YyeTcKS.NQUWekQXmtoUEFDjCEy/X02lle', 75.00, 0)/*Mot de Passe = kateSafePass*/,
    ('cdupuis', 'Dupuis', 'Charles', 'charles.dupuis@example.com', '0623456789', '30 rue Lafayette', '59000', 'Lille', '{bcrypt}$2y$10$baKiSoOwM/YQ8e1ucdl3fOFPpWr3JLOksGYcY4J.3ayzDOPS39RMq', 400.00, 1)/*Mot de Passe = CharlesPass*/,
    ('abrown', 'Brown', 'Alice', 'alice.brown@example.com', '0676543210', '45 avenue Carnot', '21000', 'Dijon', '{bcrypt}$2y$10$qZA6p/y3klGTcu9GyW8PeueiT.NbPaIQV0WtA37MoTgXQGrsOaxVK', 180.00, 0)/*Mot de Passe = BrownAlice*/,
    ('tlee', 'Lee', 'Tom', 'tom.lee@example.com', '0612340987', '78 allée des Pins', '06000', 'Nice', '{bcrypt}$2y$10$bTxvz/x2gyD35PYSoBc2vuFGU550UbaD.IieQQMObfY9E49Bz1hjq', 120.00, 0)/*Mot de Passe = Tom2023$*/;
