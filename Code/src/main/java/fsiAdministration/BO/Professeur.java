package fsiAdministration.BO;

import javafx.beans.property.SimpleStringProperty;

public class Professeur {

    private int idProfesseur;
    private SimpleStringProperty nomProfesseur;
    private SimpleStringProperty prenomProfesseur;
    private SimpleStringProperty courrielProfesseur;

    public Professeur(int idProfesseur, String nomProfesseur, String prenomProfesseur, String courrielProfesseur) {
        this.idProfesseur = idProfesseur;
        this.nomProfesseur = new SimpleStringProperty(nomProfesseur);
        this.prenomProfesseur = new SimpleStringProperty (prenomProfesseur);
        this.courrielProfesseur = new SimpleStringProperty(courrielProfesseur);
    }

    public int getIdProfesseur() {
        return idProfesseur;
    }

    public void setIdProfesseur(int idProfesseur) {
        this.idProfesseur = idProfesseur;
    }

    public String getNomProfesseur() {
        return nomProfesseur.get();
    }

    public void setNomProfesseur(String nomProfesseur) {
        this.nomProfesseur.set(nomProfesseur);
    }

    public SimpleStringProperty nomProfesseurProperty() {
        return nomProfesseur;
    }

    public String getPrenomProfesseur() {
        return prenomProfesseur.get();
    }

    public void setPrenomProfesseur(String prenomProfesseur) {
        this.prenomProfesseur.set(prenomProfesseur);
    }

    public SimpleStringProperty prenomProfesseurProperty() {
        return prenomProfesseur;
    }

    public String getCourrielProfesseur() {
        return courrielProfesseur.get();
    }

    public void setCourrielProfesseur(String courrielProfesseur) {
        this.courrielProfesseur.set(courrielProfesseur);
    }

    public SimpleStringProperty courrielProfesseurProperty() {
        return courrielProfesseur;
    }
}