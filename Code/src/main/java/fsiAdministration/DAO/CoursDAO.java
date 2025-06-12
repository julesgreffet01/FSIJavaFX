package fsiAdministration.DAO;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Etudiant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CoursDAO extends DAO<Cours> {

        @Override
        public boolean create(Cours obj) {
            boolean result = false;
            String sql = "INSERT INTO cours (libelleCours, descriptionCours, idSection, idProfesseur, volumeHoraireHebdo) VALUES (?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = this.connect.prepareStatement(sql);
                ps.setString(1, obj.getLib());
                ps.setString(2, obj.getDesc());
                ps.setInt(3, obj.getIdSection());
                ps.setInt(4, obj.getIdprofesseur());
                ps.setInt(5, obj.getVolHoraire());

                int rowsInserted = ps.executeUpdate();
                result = rowsInserted > 0;
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
                statement.setInt(1, obj.getId());
                int rowsDeleted = statement.executeUpdate();
                result = rowsDeleted > 0;
            } catch(SQLException e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        public boolean update(Cours obj) {
            boolean result = false;
            String sql = "UPDATE cours SET libelleCours = ?, descriptionCours = ?, idSection = ?, idProfesseur = ?, volumeHoraireHebdo = ? WHERE idCours = ?";
            try {
                PreparedStatement ps = this.connect.prepareStatement(sql);
                ps.setString(1, obj.getLib());
                ps.setString(2, obj.getDesc());
                ps.setInt(3, obj.getIdSection());
                ps.setInt(4, obj.getIdprofesseur());
                ps.setInt(5, obj.getVolHoraire());
                ps.setInt(6, obj.getId());

                int rowsUpdated = ps.executeUpdate();
                result = rowsUpdated > 0;
            } catch(SQLException e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        public Cours find(int id) {
            Cours cours = null;
            String query = "SELECT * FROM cours WHERE idCours = ?";
            try {
                PreparedStatement ps = this.connect.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    cours = new Cours(
                            rs.getInt("idCours"),
                            rs.getString("libelleCours"),
                            rs.getString("descriptionCours"),
                            rs.getInt("idSection"),
                            rs.getInt("idProfesseur"),
                            rs.getInt("volumeHoraireHebdo")
                    );
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
            return cours;
        }

        @Override
        public List<Cours> findAll() {
            List<Cours> mesCours = new ArrayList<>();
            try {
                String sql = "SELECT * FROM cours ORDER BY idCours";
                Statement ps = this.connect.createStatement();
                ResultSet rs = ps.executeQuery(sql);
                while (rs.next()) {
                    Cours cours = new Cours(
                            rs.getInt("idCours"),
                            rs.getString("libelleCours"),
                            rs.getString("descriptionCours"),
                            rs.getInt("idSection"),
                            rs.getInt("idProfesseur"),
                            rs.getInt("volumeHoraireHebdo")
                    );
                    mesCours.add(cours);
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
            return mesCours;
        }

        public List<Cours> getAllBySection(int idSection) {
            List<Cours> mesCours = new ArrayList<>();
            try {
                String sql = "SELECT * FROM cours WHERE idSection = ?";
                PreparedStatement ps = this.connect.prepareStatement(sql);
                ps.setInt(1, idSection);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Cours cours = new Cours(
                            rs.getInt("idCours"),
                            rs.getString("libelleCours"),
                            rs.getString("descriptionCours"),
                            rs.getInt("idSection"),
                            rs.getInt("idProfesseur"),
                            rs.getInt("volumeHoraireHebdo")
                    );
                    mesCours.add(cours);
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
            return mesCours;
        }

        public int getNbCoursBySection(int idSection) {
            int nbCours = 0;
            try {
                String sql = "SELECT COUNT(*) FROM cours WHERE idSection = ?";
                PreparedStatement ps = this.connect.prepareStatement(sql);
                ps.setInt(1, idSection);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nbCours = rs.getInt(1);
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
            return nbCours;
        }
    }

