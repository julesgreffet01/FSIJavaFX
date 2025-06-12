package fsiAdministration.BO;

public class Cours {
    private int id;
    private String lib;
    private String desc;
    private int idSection;
    private int idprofesseur;
    private int VolHoraire;

    public Cours(int id, String lib, String desc, int idSection, int idprofesseur, int volHoraire) {
        this.id = id;
        this.lib = lib;
        this.desc = desc;
        this.idSection = idSection;
        this.idprofesseur = idprofesseur;
        this.VolHoraire = volHoraire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIdSection() {
        return idSection;
    }

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }

    public int getIdprofesseur() {
        return idprofesseur;
    }

    public void setIdprofesseur(int idprofesseur) {
        this.idprofesseur = idprofesseur;
    }

    public int getVolHoraire() {
        return VolHoraire;
    }

    public void setVolHoraire(int volHoraire) {
        VolHoraire = volHoraire;
    }
}
