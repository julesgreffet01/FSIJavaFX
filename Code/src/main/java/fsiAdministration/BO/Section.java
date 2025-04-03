package fsiAdministration.BO;

public class Section {

    private int idSection;
    private String libelleSection;

    public Section(int idSection, String libelleSection) {
        this.idSection = idSection;
        this.libelleSection = libelleSection;
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

    @Override
    public String toString() {
        return this.libelleSection;
    }
}
