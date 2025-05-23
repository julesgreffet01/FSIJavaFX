package fsiAdministration.DAO;

import fsiAdministration.BO.Etudiant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import fsiAdministration.BO.Section;
import fsiAdministration.DAO.DBManager;

public class SectionDAO extends DAO<Section>{

    @Override
    public boolean create(Section obj) {
            boolean result = false;
            try {
                String query = "INSERT INTO Section ( LibelleSection) VALUES (?);";
                PreparedStatement preparedStatement = this.connect.prepareStatement(query);
                preparedStatement.setString(1, obj.getLibelleSection());
                int rows = preparedStatement.executeUpdate();
                if (rows > 0){
                    result=true;
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
            return result;
    }

    @Override
    public boolean delete(Section obj) {
        boolean result = false;
        String query = "DELETE FROM Section WHERE idSection = ?;";

        try (PreparedStatement preparedStatement = this.connect.prepareStatement(query)) {
            preparedStatement.setInt(1, obj.getIdSection());
            result = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean update(Section obj) {
        boolean result = false;
        String query = "UPDATE Section SET LibelleSection = ? WHERE idSection = ?;";
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement(query);
            preparedStatement.setString(1, obj.getLibelleSection());
            preparedStatement.setInt(2, obj.getIdSection());
            int rows = preparedStatement.executeUpdate();
            if (rows > 0){
                result=true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Section find(int id) {
        Section Section = null;
        String query = "SELECT * FROM Section WHERE idSection = ?;";
        try {
            PreparedStatement preparedStatement = this.connect.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Section = new Section(
                        resultSet.getInt("idSection"),
                        resultSet.getString("libelleSection")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Section;
    }

    @Override
    public List<Section> findAll() {
        List<Section> sections = new ArrayList<Section>();
        String query = "SELECT * FROM Section;";

        try (PreparedStatement preparedStatement = this.connect.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Section section = new Section(
                        resultSet.getInt("idSection"),
                        resultSet.getString("LibelleSection")
                );
                sections.add(section);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sections;
    }

}
