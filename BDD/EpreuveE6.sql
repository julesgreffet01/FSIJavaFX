CREATE TABLE PROFESSEUR(
  idProfesseur serial,
  nomProfesseur VARCHAR(50) NOT NULL,
  prenomProfesseur VARCHAR(50) NOT NULL,
  courrielProfesseur VARCHAR(100) NOT NULL,
  CONSTRAINT professeur_PK PRIMARY KEY(idProfesseur)
);

--///////////////////--
ALTER TABLE COURS
    --ADD volumeHoraireHebdo INT NOT NULL
    ADD idProfesseur INT
ADD CONSTRAINT cours_professeur_FK FOREIGN KEY(idProfesseur) REFERENCES PROFESSEUR(idProfesseur);

---TEST INSERT PROFESSEUR--
INSERT INTO professeur (prenomProfesseur, nomProfesseur, courrielProfesseur)
VALUES ('Bastien', 'Thouverez', 'touvtouv@gmail.com');

----LE SOUVENIR DE MOI ---
ALTER TABLE UTILISATEUR
    ADD dateDerniereConnexion TIMESTAMP;