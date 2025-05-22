package fsiAdministration.BO;

import javafx.beans.property.SimpleStringProperty;

import java.sql.Date;

public class Etudiant {

    private int idEtudiant;
    private SimpleStringProperty nomEtudiant;
    private SimpleStringProperty prenomEtudiant;
    private int idSection;
    private Date dateNaiEtu;

    public Etudiant(int idEtudiant, String nomEtudiant, String prenomEtudiant, int idSection, Date dateNaiEtu) {
        this.idEtudiant = idEtudiant;
        this.nomEtudiant = new SimpleStringProperty(nomEtudiant);
        this.prenomEtudiant = new SimpleStringProperty(prenomEtudiant);
        this.idSection = idSection;
        this.dateNaiEtu = dateNaiEtu;
    }

    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNomEtudiant() {
        return nomEtudiant.get();
    }

    public void setNomEtudiant(String nomEtudiant) {
        this.nomEtudiant.set(nomEtudiant);
    }

    public String getPrenomEtudiant() {
        return prenomEtudiant.get();
    }

    public void setPrenomEtudiant(String prenomEtudiant) {
        this.prenomEtudiant.set(prenomEtudiant);
    }

    public SimpleStringProperty nomEtudiantProperty() {
        return nomEtudiant;
    }

    public SimpleStringProperty prenomEtudiantProperty() {
        return prenomEtudiant;
    }

    public Date getDateNaiEtu() {
        return dateNaiEtu;
    }

    public void setDateNaiEtu(Date dateNaiEtu) {
        this.dateNaiEtu = dateNaiEtu;
    }
}
