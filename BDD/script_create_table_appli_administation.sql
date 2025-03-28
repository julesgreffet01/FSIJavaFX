CREATE TABLE SECTION(
   idSection serial,
   libelleSection VARCHAR(50),
   CONSTRAINT Section_PK PRIMARY KEY(idSection)
);

CREATE TABLE COURS(
   idCours serial,
   libelleCours VARCHAR(20) NOT NULL,
   DescriptionCours VARCHAR(100) NOT NULL,
   idSection INT NOT NULL,
   CONSTRAINT cours_PK PRIMARY KEY(idCours),
   CONSTRAINT cours_section_FK FOREIGN KEY(idSection) REFERENCES SECTION(idSection)
);

CREATE TABLE ETUDIANT(
   idEtudiant serial,
   nomEtudiant VARCHAR(50) NOT NULL,
   prenomEtudiant VARCHAR(50) NOT NULL,
   idSection INT NOT NULL,
   CONSTRAINT eleve_PK PRIMARY KEY(idEtudiant),
   CONSTRAINT eleve_section_FK FOREIGN KEY(idSection) REFERENCES SECTION(idSection)
);


CREATE TABLE UTILISATEUR(
   idUtilisateur serial,
   loginUtilisateur VARCHAR(50) NOT NULL,
   mdpUtilisateur VARCHAR(255) NOT NULL,
   CONSTRAINT utilisateur_PK PRIMARY KEY(idUtilisateur)
);
