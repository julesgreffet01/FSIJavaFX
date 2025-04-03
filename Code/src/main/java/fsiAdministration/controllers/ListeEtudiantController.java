package fsiAdministration.controllers;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.DAO.EtudiantDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListeEtudiantController extends MenuController implements Initializable {
    @FXML
    private TableView<Etudiant> tvEtudiants;

    @FXML
    private TableColumn<Etudiant, String> tcNomEtud;

    @FXML
    private TableColumn<Etudiant, String> tcPrenomEtud;

    @FXML
    private Button bRetour;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


//        EtudiantDAO etudDAO = new EtudiantDAO();
//        List<Etudiant> mesEtud = etudDAO.findAll();
//        ObservableList<Etudiant> mesEtudOL= FXCollections.observableArrayList(mesEtud);
//        tcNomEtud.setCellValueFactory(cellData -> cellData.getValue().nomEtudiantProperty());
//        tcPrenomEtud.setCellValueFactory(cellData -> cellData.getValue().prenomEtudiantProperty());
//
//        tvEtudiants.setItems(mesEtudOL);


        tcNomEtud.setCellValueFactory(new PropertyValueFactory<>("nomEtudiant"));
        tcPrenomEtud.setCellValueFactory(new PropertyValueFactory<>("prenomEtudiant"));
        ObservableList<Etudiant> data = getUserList();
        tvEtudiants.setItems(data);
    }

    private ObservableList<Etudiant> getUserList() {

        EtudiantDAO etudDAO = new EtudiantDAO();
        List<Etudiant> mesEtud = etudDAO.findAll();

        ObservableList<Etudiant> list = FXCollections.observableArrayList(mesEtud);
        return list;
    }

    @FXML
    private void bRetourClick(){
        // On fait le lien avec l'ecran actuelle
        Stage stageP = (Stage) bRetour.getScene().getWindow();
        //on ferme l'écran
        stageP.close();


        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
            Parent root = fxmlLoader.load();

            AccueilController accueilController = fxmlLoader.getController();

            // Créer une nouvelle fenêtre (Stage)
            Stage stage = new Stage();
            stage.setTitle("Liste etudiant");
            stage.setScene(new Scene(root));

            // Configurer la fenêtre en tant que modal
            stage.initModality(Modality.APPLICATION_MODAL);

            // Afficher la fenêtre et attendre qu'elle se ferme
            stage.show();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }



}
