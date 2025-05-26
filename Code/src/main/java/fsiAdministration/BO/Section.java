package fsiAdministration.BO;

import fsiAdministration.DAO.EtudiantDAO;

public class Section {

    private int idSection;
    private String libelleSection;
    private int nbEtu;

    public Section(int idSection, String libelleSection) {
        this.idSection = idSection;
        this.libelleSection = libelleSection;
        if(idSection > 0){
            EtudiantDAO etuDAO = new EtudiantDAO();
            this.nbEtu = etuDAO.getNbEtuByIdSection(this.idSection);
        }
    }

    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    public String getLibelleSection() {
        return libelleSection;
    }

    public void setLibelleSection(String libelleSection) {
        this.libelleSection = libelleSection;
    }

    public void setNbEtu() {
        EtudiantDAO etuDAO = new EtudiantDAO();
        this.nbEtu = etuDAO.getNbEtuByIdSection(this.idSection);
    }

    public int getNbEtu() {
        return nbEtu;
    }

    @Override
    public String toString() {
        return this.libelleSection;
    }
}
