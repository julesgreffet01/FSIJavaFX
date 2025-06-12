package fsiAdministration.BO;

import java.sql.Timestamp;

public class Utilisateur {

    private int idUtilisateur;
    private String loginUtilisateur;
    private String  mdpUtilisateur;
    private Timestamp dateDerniereConnexion;

    public Utilisateur(int idUtilisateur, String loginUtilisateur, String mdpUtilisateur, Timestamp dateDerniereConnexion) {
        this.idUtilisateur = idUtilisateur;
        this.loginUtilisateur = loginUtilisateur;
        this.mdpUtilisateur = mdpUtilisateur;
        this.dateDerniereConnexion = dateDerniereConnexion;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getMdpUtilisateur() {
        return mdpUtilisateur;
    }

    public void setMdpUtilisateur(String mdpUtilisateur) {
        this.mdpUtilisateur = mdpUtilisateur;
    }

    public String getLoginUtilisateur() {
        return loginUtilisateur;
    }

    public void setLoginUtilisateur(String loginUtilisateur) {
        this.loginUtilisateur = loginUtilisateur;
    }

    public Timestamp getDateDerniereConnexion() {
        return dateDerniereConnexion;
    }

    public void setDateDerniereConnexion(Timestamp dateDerniereConnexion) {
        this.dateDerniereConnexion = dateDerniereConnexion;
    }

    public Utilisateur() {
    }
}
