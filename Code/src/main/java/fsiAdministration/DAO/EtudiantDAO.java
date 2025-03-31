package fsiAdministration.DAO;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Utilisateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAO extends DAO<Etudiant>{


    @Override
    public boolean create(Etudiant obj) {
        boolean controle = false;
        try{
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FSI_GestionAdmin","postgres","cannelle01");
//            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5433/FSI_GestionAdmin","postgres","postgreSQL");
            String sql = "Insert into Etudiant(nomEtudiant, prenomEtudiant, idSection) values (?,?,?);";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1,obj.getNomEtudiant());
            statement.setString(2,obj.getPrenomEtudiant());
            statement.setInt(3,obj.getIdSection());

            int rowsInserer = statement.executeUpdate();
            if (rowsInserer > 0) {
                controle= true;
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return controle;
    }

    public int lastId(){
        int controle = 1;

        try {
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FSI_GestionAdmin","postgres","cannelle01");
//            Connection  connect = DriverManager.getConnection("jdbc:postgresql://localhost:5433/FSI_GestionAdmin","postgres","postgreSQL");

            ResultSet result = connect.createStatement().executeQuery("select max(idEtudiant) from Etudiant ");
            if(result.next()){
                controle = result.getInt(1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return controle;
    }

    @Override
    public boolean delete(Etudiant obj) {
        return false;
    }

    @Override
    public boolean update(Etudiant obj) {
        return false;
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
            Class.forName("org.postgresql.Driver");
            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FSI_GestionAdmin","postgres","cannelle01");
//            Connection  connect = DriverManager.getConnection("jdbc:postgresql://localhost:5433/FSI_GestionAdmin","postgres","postgreSQL");

            String sql = "SELECT * FROM etudiant";
            Statement ps = connect.createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while(rs.next()) {
                etud = new Etudiant(
                        rs.getInt("idEtudiant"),
                        rs.getString ("nomEtudiant"),
                        rs.getString("prenomEtudiant"),
                        rs.getInt("idSection")
                        );
                mesEtud.add(etud);
            }

        } catch (SQLException e) {
            return null;
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return mesEtud;
    }
}
