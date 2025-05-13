package fsiAdministration.DAO;

import fsiAdministration.BO.Cours;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO extends DAO<Cours>{
    @Override
    public boolean create(Cours obj) {
        return false;
    }

    @Override
    public boolean delete(Cours obj) {
        return false;
    }

    @Override
    public boolean update(Cours obj) {
        return false;
    }

    @Override
    public Cours find(int id) {
        return null;
    }

    @Override
    public List<Cours> findAll() {
        List<Cours> mesCours = new ArrayList<>();
        Cours cours;
        try {
            String sql = "select * from cours where idEtudiant=?";
            Statement ps = this.connect.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next()) {
                cours = new Cours(
                        rs.getInt("idcours"),
                        rs.getString("libellecours"),
                        rs.getString("descriptioncours"),
                        rs.getInt("idSection")
                );
                mesCours.add(cours);
            }
        } catch (SQLException e) {
            return null;
        }
        return mesCours;
    }
}
