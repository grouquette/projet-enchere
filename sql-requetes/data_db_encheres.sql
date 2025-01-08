
use [projet-enchere]
go

INSERT INTO [ROLES] ([ROLE],[IS_ADMIN]) VALUES ('ROLE_MEMBRE',0);
INSERT INTO [ROLES] ([ROLE],[IS_ADMIN]) VALUES ('ROLE_MEMBRE',1);
INSERT INTO [ROLES] ([ROLE],[IS_ADMIN]) VALUES ('ROLE_ADMIN',1);


-- Email et pseudo uniques pour ne pas avoir plusieurs utilisateurs identiques--
ALTER TABLE UTILISATEURS ADD CONSTRAINT UN_email UNIQUE (email);

ALTER TABLE UTILISATEURS ADD CONSTRAINT UN_pseudo UNIQUE (pseudo);


-- Email et pseudo de la bonne forme

ALTER TABLE UTILISATEURS ADD CONSTRAINT CK_email CHECK (email like '%@%.%');

ALTER TABLE UTILISATEURS ADD CONSTRAINT CK_pseudo CHECK (pseudo not like '%[^A-Za-Z0-9]%');

-- code postal du bon format--

ALTER TABLE UTILISATEURS ADD CONSTRAINT CK_code_postal CHECK(code_postal BETWEEN '01000' AND '95999');

-- Prix initial ne doit pas être inférieur à 0 et prix de vente ne doit pas être inférieur à prix initial --

ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT CK_prix_initial CHECK(prix_initial >= 0);

ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT CK_prix_vente CHECK(prix_vente >= prix_initial);


-- Insertion de données dans utilisateurs--

INSERT INTO Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)
VALUES
    ('jdoe', 'Doe', 'John', 'john.doe@example.com', '0123456789', '10 rue de Paris', '75001', 'Paris', '$2y$10$uQME.1FDRC4f3MTiQmv.WuWA6KjiPBPT1IBgF.qND5sFCrgA58x0m', 100.00, 0)/*Mot de Passe = password123*/,
    ('asmith', 'Smith', 'Anna', 'anna.smith@example.com', '0123456790', '20 avenue des Champs', '75008', 'Paris', '$2y$10$yRjXjdryx6UjQR/jIyirceQubr04y0S..rkTe0puJilc155hdtxjm', 250.50, 0)/*Mot de Passe = securePass1*/,
    ('jdupont', 'Dupont', 'Jean', 'jean.dupont@example.com', '0654321987', '5 place de l''étoile', '69001', 'Lyon', '$2y$10$fdT.k1LH.NACI4etrLfSJecXKUz3LDL6UpmGTXeUfNylsqE.fLGeG', 300.00, 1)/*Mot de Passe = jeanPass2024*/,
    ('claporte', 'Laporte', 'Claire', 'claire.laporte@example.com', '0678192345', '12 boulevard Haussmann', '75009', 'Paris', '$2y$10$nPs7xuVP2p.IP2Ldcd7TNuS0upGhx2JsRGVicPiRn3QZgFPAiKJMq', 500.00, 1)/*Mot de Passe = claireAdmin*/,
    ('mpaul', 'Paul', 'Martin', 'martin.paul@example.com', '0612345678', '8 rue Victor Hugo', '33000', 'Bordeaux', '$2y$10$V.dUk7yvAfSiXLAKxlzWpeN2IX4flXzCEh3bBkWjyrUvq4UMar4o6', 150.00, 0)/*Mot de Passe = martin123*/,
    ('elou', 'Lou', 'Emma', 'emma.lou@example.com', '0698123456', '3 chemin des Fleurs', '13000', 'Marseille', '$2y$10$wWEXBHXpST6oA6OVJekrn.Ii92DvOZ3fZ/3UJAeyz.j.WH9FhaxWe!', 200.00, 0)/*Mot de Passe = emma2023!*/,
    ('kroger', 'Rogers', 'Kate', 'kate.rogers@example.com', '0601234567', '15 rue Saint-Jacques', '67000', 'Strasbourg', '$2y$10$H4Xvh8hMoPfVkUFGfU1YyeTcKS.NQUWekQXmtoUEFDjCEy/X02lle', 75.00, 0)/*Mot de Passe = kateSafePass*/,
    ('cdupuis', 'Dupuis', 'Charles', 'charles.dupuis@example.com', '0623456789', '30 rue Lafayette', '59000', 'Lille', '$2y$10$baKiSoOwM/YQ8e1ucdl3fOFPpWr3JLOksGYcY4J.3ayzDOPS39RMq', 400.00, 1)/*Mot de Passe = CharlesPass*/,
    ('abrown', 'Brown', 'Alice', 'alice.brown@example.com', '0676543210', '45 avenue Carnot', '21000', 'Dijon', '$2y$10$qZA6p/y3klGTcu9GyW8PeueiT.NbPaIQV0WtA37MoTgXQGrsOaxVK', 180.00, 0)/*Mot de Passe = BrownAlice*/,
    ('tlee', 'Lee', 'Tom', 'tom.lee@example.com', '0612340987', '78 allée des Pins', '06000', 'Nice', '$2y$10$bTxvz/x2gyD35PYSoBc2vuFGU550UbaD.IieQQMObfY9E49Bz1hjq', 120.00, 0)/*Mot de Passe = Tom2023$*/;


-- Insertion de donnÃ©es dans categories--

INSERT INTO Categories (libelle)
VALUES
('informatique'),
('jouets'),
('outils'),
('téléphones');
INSERT INTO Categories (libelle)
VALUES
('électroménager'),
('mobilier'),
('vêtements'),
('sport'),
('livres'),
('musique'),
('jeux vidéo'),
('automobile'),
('jardinage'),
('décoration'),
('bijoux'),
('photographie'),
('santé'),
('voyages');


-- Insertion de donnÃ©es dans articles--

INSERT INTO Articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)
VALUES
('Ordinateur Acer Aspire 1 A115-32-C3AK', 'Ordinateur portable de marque Acer 15,6 pouces gris. 128 Go de mémoire, processeur Intel Celeron B830 4Go de mémoire vive Windows 11 S', GETDATE(), GETDATE()+3, 5, 5, 1, 1),
('Cuisine bon appétit 23 accessoires', 'Cuisine contemporaine avec nombreuses fonctionnalités. Module électronique. Four, frigo, évier, machine à espresso. 23 accessoires inclus. Hauteur plan de travail : 48.5cm.', GETDATE(), GETDATE()+5, 2, 2, 3,2),
('Perceuse-visseuse sans fil 18V', 'Perceuse visseuse Makita 18V neuve. Modèle DDF453SYE, Moteur brushless sans charbon plus durable et performant, Vendu avec une batterie et chargeur', GETDATE(), GETDATE()+10, 6, 6, 3, 3),
('SAMSUNG Galaxy A15 4GB+128GB (Bleu Nuit)', 'Samsung Galaxy A15 4G 4Go de RAM, 128 Go de mémoire, écran 6,5 pouces avec une résolution de 1080 x 1920. Bon état', GETDATE(), GETDATE()+4, 4, 4, 5,4); 
INSERT INTO Articles_vendus (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie)
VALUES
-- électronique
('Apple iPhone 13 Pro Max 256GB', 'Smartphone Apple avec écran 6,7 pouces, triple caméra 12 MP, et stockage 256 Go. Couleur Graphite.', GETDATE(), GETDATE() + 7, 950, 1200, 2, 1),
('écouteurs Bluetooth Sony WH-1000XM4', 'écouteurs sans fil avec réduction de bruit active, autonomie de 30 heures, couleur noir.', GETDATE(), GETDATE() + 5, 150, 200, 4, 1),

-- Maison et jardin
('Aspirateur Dyson V15 Detect', 'Aspirateur sans fil Dyson, détecteur laser pour poussières fines, autonomie 60 minutes.', GETDATE(), GETDATE() + 10, 500, 550, 3, 2),
('Set de jardin en bois acacia', 'Ensemble de table et chaises pour 6 personnes, matériau durable et rÃ©sistant aux intempéries.', GETDATE(), GETDATE() + 14, 300, 400, 6, 2),

-- Mode et accessoires
('Montre connectée Garmin Fenix 6 Pro', 'Montre GPS multisports avec musique, navigation et cartographie intégrées.', GETDATE(), GETDATE() + 10, 450, 490, 7, 3),
('Sac à main cuir Michael Kors', 'Sac à main en cuir noir véritable, modèle classique, poche intérieure zippée.', GETDATE(), GETDATE() + 6, 200, 220, 5, 3),

-- Loisirs
('Vélo tout terrain Rockrider ST 540', 'VTT 27,5 pouces avec suspension avant, 24 vitesses, cadre en aluminium.', GETDATE(), GETDATE() + 8, 300, 350, 8, 4),
('Jeu de société Catan', 'Jeu de stratégie pour 3 à 4 joueurs, édition 2025.', GETDATE(), GETDATE() + 4, 30, 40, 2, 4),

-- Produits culturels
('Livre "Les Misérables" de Victor Hugo', 'édition collector reliée, couverture cuir, illustrations origine.', GETDATE(), GETDATE() + 12, 25, 35, 1, 5),
('Vinyle "Thriller" de Michael Jackson', 'Album légendaire en édition originale, 1982.', GETDATE(), GETDATE() + 9, 50, 70, 9, 5),

-- Sport
('Raquette de tennis Wilson Pro Staff 97', 'Raquette en graphite, utilisée par Roger Federer, poids 315g.', GETDATE(), GETDATE() + 7, 180, 200, 10, 6),
('Chaussures de course Nike Air Zoom Pegasus', 'Chaussures de running légères et confortables, taille 42.', GETDATE(), GETDATE() + 5, 120, 150, 6, 6);

-- Insertion de données dans Retrait

INSERT INTO RETRAITS (no_article, rue, code_postal, ville)
VALUES
(1, '3, rue des alouettes', '44800' , 'Saint-Herblain'),
(2, '25, rue des sapins', '49000', 'Angers'),
(3, '230, allée des brumes', '85200', 'La Roche sur Yon'),
(4, '2 boulevard des chats', '44300', 'Nantes');
INSERT INTO RETRAITS (no_article, rue, code_postal, ville)
VALUES
-- Correspondant aux articles déjà  insérés
(5, '15, avenue de la liberté', '75001', 'Paris'),
(6, '12, rue des marguerites', '31000', 'Toulouse'),
(7, '45, impasse des lilas', '13001', 'Marseille'),
(8, '5, chemin des peupliers', '67000', 'Strasbourg'),
(9, '33, boulevard des marronniers', '59000', 'Lille'),
(10, '8, allée des pins', '80000', 'Amiens'),
(11, '21, rue de la mer', '14000', 'Caen'),
(12, '19, avenue des fleurs', '69000', 'Lyon'),
(13, '7, rue des collines', '33000', 'Bordeaux'),
(14, '23, place des églises', '76000', 'Rouen');
INSERT INTO RETRAITS (no_article, rue, code_postal, ville)
VALUES
-- Correspondant aux articles déjà insérés
(15, '15, avenue de la liberté', '75001', 'Paris'),
(16, '12, rue des marguerites', '31000', 'Toulouse');
