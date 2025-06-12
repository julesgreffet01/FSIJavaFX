package fsiAdministration.DAO;

import fsiAdministration.BO.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UtilisateurDAO extends DAO<Utilisateur> {

    @Override
    public boolean create(Utilisateur obj) {
        boolean result = false;
        try {
            String sql = "INSERT INTO Utilisateur(loginUtilisateur, mdpUtilisateur, dateDerniereConnexion) VALUES(?,?,?)";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setString(1, obj.getLoginUtilisateur());
            ps.setString(2, obj.getMdpUtilisateur());
            ps.setTimestamp(3, obj.getDateDerniereConnexion()); // valeur actuelle ou null
            int rowsInserer = ps.executeUpdate();
            result = rowsInserer > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(Utilisateur obj) {
        boolean result = false;
        try {
            String sql = "DELETE FROM Utilisateur WHERE idUtilisateur = ?";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, obj.getIdUtilisateur());
            int rowsInserer = ps.executeUpdate();
            result = rowsInserer > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Utilisateur obj) {
        boolean result = false;
        try {
            String sql = "UPDATE Utilisateur SET loginUtilisateur=?, mdpUtilisateur=?, dateDerniereConnexion=? WHERE idUtilisateur = ?";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setString(1, obj.getLoginUtilisateur());
            ps.setString(2, obj.getMdpUtilisateur());
            ps.setTimestamp(3, obj.getDateDerniereConnexion());
            ps.setInt(4, obj.getIdUtilisateur());
            int rowsInserer = ps.executeUpdate();
            result = rowsInserer > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Utilisateur find(int idUtilisateur) {
        Utilisateur user = null;
        try {
            String sql = "SELECT * FROM utilisateur WHERE idUtilisateur = ?";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setInt(1, idUtilisateur);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new Utilisateur(
                        rs.getInt("idUtilisateur"),
                        rs.getString("mdpUtilisateur"),
                        rs.getString("loginUtilisateur"),
                        rs.getTimestamp("dateDerniereConnexion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Utilisateur> findAll() {
        List<Utilisateur> mesUtis = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Utilisateur";
            Statement statement = this.connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Utilisateur uti = new Utilisateur(
                        rs.getInt("idUtilisateur"),
                        rs.getString("mdpUtilisateur"),
                        rs.getString("loginUtilisateur"),
                        rs.getTimestamp("dateDerniereConnexion")
                );
                mesUtis.add(uti);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mesUtis;
    }

    public Utilisateur connexion(String login, String password) {
        Utilisateur user = null;
        try {
            String sql = "SELECT * FROM utilisateur WHERE loginUtilisateur = ? AND mdpUtilisateur = ?";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet result = ps.executeQuery();

            if (result.next()) {
                // Lecture de l'utilisateur
                user = new Utilisateur(
                        result.getInt("idUtilisateur"),
                        result.getString("mdpUtilisateur"),
                        result.getString("loginUtilisateur"),
                        result.getTimestamp("dateDerniereConnexion")
                );

                // Mise à jour de la dernière connexion
                String updateSql = "UPDATE utilisateur SET dateDerniereConnexion = CURRENT_TIMESTAMP WHERE idUtilisateur = ?";
                PreparedStatement updatePs = this.connect.prepareStatement(updateSql);
                updatePs.setInt(1, user.getIdUtilisateur());
                updatePs.executeUpdate();

                // Mise à jour aussi dans l'objet Java
                user.setDateDerniereConnexion(new Timestamp(System.currentTimeMillis()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public Utilisateur getDernierUtilisateurConnecte() {
        Utilisateur user = null;
        try {
            String sql = "SELECT * FROM utilisateur WHERE dateDerniereConnexion IS NOT NULL ORDER BY dateDerniereConnexion DESC LIMIT 1";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new Utilisateur(
                        rs.getInt("idUtilisateur"),
                        rs.getString("loginUtilisateur"),
                        rs.getString("mdpUtilisateur"),
                        rs.getTimestamp("dateDerniereConnexion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}