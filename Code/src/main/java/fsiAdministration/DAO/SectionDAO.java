package fsiAdministration.DAO;

import fsiAdministration.BO.Etudiant;

import java.util.List;

public class SectionDAO extends DAO<Etudiant>{
    @Override
    public boolean create(Etudiant obj) {
        return false;
    }

    @Override
    public boolean delete(Etudiant obj) {
        return false;
    }

    @Override
    public boolean update(Etudiant obj) {
        return false;
    }

    @Override
    public Etudiant find(int id) {
        return null;
    }

    @Override
    public List<Etudiant> findAll() {

        return null;
    }
}
