package fsiAdministration.DAO;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Section;
import fsiAdministration.BO.Utilisateur;
import javafx.beans.property.SimpleStringProperty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO extends DAO<Etudiant>{


    @Override
    public boolean create(Etudiant obj) {
        boolean controle = false;
        try{
            String sql = "Insert into Etudiant(nomEtudiant, prenomEtudiant, idSection, dateNai) values (?,?,?,?);";
            PreparedStatement statement = this.connect.prepareStatement(sql);
            statement.setString(1,obj.getNomEtudiant());
            statement.setString(2,obj.getPrenomEtudiant());
            statement.setInt(3,obj.getIdSection());
            statement.setDate(4,obj.getDateNaiEtu());

            int rowsInserer = statement.executeUpdate();
            if (rowsInserer > 0) {
                controle= true;
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return controle;
    }

    public Integer getNbEtuByIdSection(int idSection){
        int result = 0;
        try  {
            String sql = "Select Count(*) from Etudiant where idSection = ?";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1,idSection);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                result = rs.getInt(1);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Etudiant obj) {
        boolean controle = false;
        try{
            String sql = "DELETE FROM Etudiant WHERE idEtudiant = ?;";
            PreparedStatement statement = this.connect.prepareStatement(sql);
            statement.setInt(1,obj.getIdEtudiant());

            int rowsInserer = statement.executeUpdate();
            if (rowsInserer > 0) {
                controle = true;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean update(Etudiant obj) {
        boolean controle = false;
        try {
            String query = "UPDATE Etudiant SET nometudiant = ?, prenometudiant = ?, idsection = ?, datenai = ? WHERE idetudiant = ?";
            PreparedStatement statement = this.connect.prepareStatement(query);
            statement.setString(1,obj.getNomEtudiant());
            statement.setString(2,obj.getPrenomEtudiant());
            statement.setInt(3,obj.getIdSection());
            statement.setDate(4,obj.getDateNaiEtu());
            statement.setInt(5,obj.getIdEtudiant());

            int rowsInserer = statement.executeUpdate();
            if (rowsInserer > 0) {
                controle = true;
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public Etudiant find(int id) {
        Etudiant etudiant = null;
        String query = "SELECT * FROM Etudiant WHERE idEtudiant = ?;";
        try {
            PreparedStatement ps = this.connect.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet.next()){
                etudiant = new Etudiant(
                        resultSet.getInt("idEtudiant"),
                        resultSet.getString("nomEtudiant"),
                        resultSet.getString("prenomEtudiant"),
                        resultSet.getInt("idSection"),
                        resultSet.getDate("dateNaiEtu")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etudiant;
    }

    @Override
    public List findAll() {
        List<Etudiant> mesEtud = new ArrayList<>();
        Etudiant etud;

        try {
            String sql = "SELECT * FROM etudiant ORDER BY idetudiant";
            Statement ps = this.connect.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next()) {
                etud = new Etudiant(
                        rs.getInt("idEtudiant"),
                        rs.getString("nomEtudiant"),
                        rs.getString("prenomEtudiant"),
                        rs.getInt("idSection"),
                        rs.getDate("DateNai")
                        );
                mesEtud.add(etud);
            }

        } catch (SQLException e) {
            return null;
        }
        return mesEtud;
    }

    public List getAllBySection(int idSection) {
        List<Etudiant> mesEtud = new ArrayList<>();
        Etudiant etud;
        try {
            String sql = "SELECT * FROM etudiant WHERE idSection = ?";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, idSection);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                etud = new Etudiant(
                        rs.getInt("idEtudiant"),
                        rs.getString("nomEtudiant"),
                        rs.getString("prenomEtudiant"),
                        rs.getInt("idSection"),
                        rs.getDate("DateNai")
                );
                mesEtud.add(etud);
            }
        } catch (SQLException e) {
            return null;
        }
        return mesEtud;
    }
}