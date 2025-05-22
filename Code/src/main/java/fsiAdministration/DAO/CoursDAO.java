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
        boolean result = false;
        String sql = "DELETE FROM cours WHERE idCours = ?";
        try {
            PreparedStatement statement = this.connect.prepareStatement(sql);
            statement.setInt(1,obj.getId());
            int rowsInserer = statement.executeUpdate();
            if (rowsInserer > 0) {
                result = true;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Cours obj) {
        boolean result = false;
        String sql = "UPDATE Cours SET libellecours=?, descriptioncours=?, idSection=? WHERE idCours= ?";
        try {
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setString(1, obj.getLib());
            ps.setString(2, obj.getDesc());
            ps.setInt(3, obj.getIdSection());
            ps.setInt(4, obj.getId());
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
