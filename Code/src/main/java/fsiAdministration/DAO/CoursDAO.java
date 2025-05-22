package fsiAdministration.DAO;

import fsiAdministration.BO.Cours;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CoursDAO extends DAO<Cours>{
    @Override
    public boolean create(Cours obj) {
        boolean result = false;
        String sql = "INSERT INTO cours (libellecours, descriptioncours, idSection) VALUES (?,?,?)";
        try {
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setString(1, obj.getLib());
            ps.setString(2, obj.getDesc());
            ps.setInt(3, obj.getIdSection());

            int rowsInserer = ps.executeUpdate();
            if (rowsInserer > 0) {
                result = true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return result;
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
            String sql = "select * from cours";
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
