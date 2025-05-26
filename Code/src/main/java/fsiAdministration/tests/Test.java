package fsiAdministration.tests;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.DAO.EtudiantDAO;

import java.sql.Date;

public class Test {
    public String testEtudiant(){
        String result = "";
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        if(!etudiantDAO.findAll().isEmpty()){
            result += "probleme de findAll; ";
        }
        Etudiant etudiant = new Etudiant(0, "test", "test", 1, new Date(0));
        boolean controle = etudiantDAO.create(etudiant);
        if(!controle){
            result += "probleme de create; ";
        }
        return result;
    }
}
