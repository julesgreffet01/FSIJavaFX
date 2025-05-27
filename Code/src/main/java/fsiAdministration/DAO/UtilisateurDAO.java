package fsiAdministration.DAO;

import fsiAdministration.BO.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO extends DAO<Utilisateur>{


    @Override
    public boolean create(Utilisateur obj) {
        boolean result = false;
        try {
            String sql = "Insert into Utilisateur(loginUtilisateur, mdpUtilisateur) VALUES(?,?)";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setString(1, obj.getLoginUtilisateur());
            ps.setString(2, obj.getMdpUtilisateur());
            int rowsInserer = ps.executeUpdate();
            if (rowsInserer > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Utilisateur obj) {
        boolean result = false;
        try {
            String sql = "Delete from Utilisateur where idUtilisateur = ?";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, obj.getIdUtilisateur());

            int rowsInserer = ps.executeUpdate();
            if (rowsInserer > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Utilisateur obj) {
        boolean result = false;
        try {
            String sql = "Update Utilisateur SET loginUtilisateur=?, mdpUtilisateur=? WHERE idUtilisateur = ?";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setString(1, obj.getLoginUtilisateur());
            ps.setString(2, obj.getMdpUtilisateur());
            ps.setInt(3, obj.getIdUtilisateur());
            int rowsInserer = ps.executeUpdate();
            if (rowsInserer > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> mesUtis = new ArrayList<>();
        Utilisateur uti;
        try {
            String sql = "Select * from Utilisateur";
            Statement statement = this.connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                uti = new Utilisateur(
                        rs.getInt("idUtilisateur"),
                        null,
                        rs.getString("loginUtilisateur")
                );
                mesUtis.add(uti);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesUtis;
    }

    @Override
    public Utilisateur find(int idEtu) {
        Utilisateur user;
        try {
            String sql = "SELECT * FROM utilisateur WHERE idetudiant = ?";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, idEtu);
            ResultSet result = ps.executeQuery();
            if(result.next()) {
                user = new Utilisateur(
                        result.getInt("idutilisateur"),
                        result.getString("mdputilisateur"),
                        result.getString("loginutilisateur"));
            } else {
                user = null;
            }

        } catch (SQLException e) {
            return null;
        }
        return user;
    }

    public Utilisateur connexion(String login, String password) {
        Utilisateur user = null;
        try {
            String sql = "SELECT * FROM utilisateur WHERE loginUtilisateur =? and mdpUtilisateur=?";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet result = ps.executeQuery();
            if(result.next()) {
                user = new Utilisateur(
                        result.getInt("idutilisateur"),
                        null,
                        result.getString("loginutilisateur"));
            }
        } catch (SQLException e) {
            return null;
        }
        return user;
    }
}
