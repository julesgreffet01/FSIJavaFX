insert into utilisateur (loginutilisateur, mdputilisateur) values ('Magali','mgo');

insert into section (libelleSection) values ('Licence développeur et infrastructure'), ('Management de projet ');

insert into etudiant (nomEtudiant, prenometudiant, idSection) values 
('Singier','Romain',1),
('Dupont','Remy',1),
('Castre','Alexandra',1),
('Hazaki','Tokyo',2),
('Fournil','Dimitri',2);

insert into cours (libelleCours, descriptionCours, idSection) values 
	('Front-end','Cours pour prendre en main les bases du developpement Front', 1),
	('UML','Cours sur les concepts de base de la modélisation UML', 1);