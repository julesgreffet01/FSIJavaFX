package fsiAdministration.DAO;

import fsiAdministration.BO.Professeur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurDAO extends DAO<Professeur> {

    @Override
    public boolean create(Professeur obj) {
        boolean controle = false;
        try {
            String sql = "INSERT INTO Professeur(nomProfesseur, prenomProfesseur, courrielProfesseur) VALUES (?, ?, ?)";
            PreparedStatement statement = this.connect.prepareStatement(sql);
            statement.setString(1, obj.getNomProfesseur());
            statement.setString(2, obj.getPrenomProfesseur());
            statement.setString(3, obj.getCourrielProfesseur());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                controle = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean delete(Professeur obj) {
        boolean controle = false;
        try {
            String sql = "DELETE FROM Professeur WHERE idProfesseur = ?";
            PreparedStatement statement = this.connect.prepareStatement(sql);
            statement.setInt(1, obj.getIdProfesseur());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                controle = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean update(Professeur obj) {
        boolean controle = false;
        try {
            String sql = "UPDATE Professeur SET nomProfesseur = ?, prenomProfesseur = ?, courrielProfesseur = ? WHERE idProfesseur = ?";
            PreparedStatement statement = this.connect.prepareStatement(sql);
            statement.setString(1, obj.getNomProfesseur());
            statement.setString(2, obj.getPrenomProfesseur());
            statement.setString(3, obj.getCourrielProfesseur());
            statement.setInt(4, obj.getIdProfesseur());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                controle = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public Professeur find(int id) {
        Professeur professeur = null;
        String sql = "SELECT * FROM Professeur WHERE idProfesseur = ?";
        try {
            PreparedStatement statement = this.connect.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                professeur = new Professeur(
                        rs.getInt("idProfesseur"),
                        rs.getString("nomProfesseur"),
                        rs.getString("prenomProfesseur"),
                        rs.getString("courrielProfesseur")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professeur;
    }

    @Override
    public List<Professeur> findAll() {
        List<Professeur> professeurs = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Professeur ORDER BY idProfesseur";
            Statement statement = this.connect.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Professeur professeur = new Professeur(
                        rs.getInt("idProfesseur"),
                        rs.getString("nomProfesseur"),
                        rs.getString("prenomProfesseur"),
                        rs.getString("courrielProfesseur")
                );
                professeurs.add(professeur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professeurs;
    }
}