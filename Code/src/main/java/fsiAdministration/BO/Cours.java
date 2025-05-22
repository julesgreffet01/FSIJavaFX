package fsiAdministration.BO;

public class Cours {
    private int id;
    private String lib;
    private String desc;
    private int idSection;

    public Cours(int id, String lib, String desc, int idSection) {
        this.id = id;
        this.lib = lib;
        this.desc = desc;
        this.idSection = idSection;
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
}
