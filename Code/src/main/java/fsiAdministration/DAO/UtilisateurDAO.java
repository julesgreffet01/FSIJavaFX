package fsiAdministration.DAO;

import fsiAdministration.BO.Utilisateur;

import java.sql.*;
import java.util.List;

public class UtilisateurDAO extends DAO<Utilisateur>{


    @Override
    public boolean create(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean delete(Utilisateur obj) {
        return false;
    }

    @Override
    public boolean update(Utilisateur obj) {
        return false;
    }

    @Override
    public Utilisateur find(int id) {
        return null;
    }

    @Override
    public List<Utilisateur> findAll() {
        return List.of();
    }

    public Utilisateur find(String login, String password) {
        Utilisateur user = new Utilisateur();
        try {
            String sql = "SELECT * FROM utilisateur WHERE loginUtilisateur =? and mdpUtilisateur=?";
            PreparedStatement ps = this.connect.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
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
}
