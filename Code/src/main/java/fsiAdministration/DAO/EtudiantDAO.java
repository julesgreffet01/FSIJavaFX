package fsiAdministration.DAO;

import fsiAdministration.BO.Etudiant;
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

    public int lastId(){
        int controle = 1;

        try {
            ResultSet result = this.connect.createStatement().executeQuery("select max(idEtudiant) from Etudiant ");
            if(result.next()){
                controle = result.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
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
        return null;
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
}