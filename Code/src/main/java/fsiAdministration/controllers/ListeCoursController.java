package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.DAO.CoursDAO;
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

public class ListeCoursController extends MenuController implements Initializable {

    @FXML
    private TableView<Cours> tvCours;

    @FXML
    private TableColumn<Cours, String> tcLib;

    @FXML
    private TableColumn<Cours, Void> tcVp;

    @FXML
    private Button bRetour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tcLib.setCellValueFactory(new PropertyValueFactory<>("lib"));
        ObservableList<Cours> data = getCoursList();
        tvCours.setItems(data);
    }

    private ObservableList<Cours> getCoursList() {
        CoursDAO coursDAO = new CoursDAO();
        List<Cours> mesCours = coursDAO.findAll();
        ObservableList<Cours> list = FXCollections.observableArrayList(mesCours);
        return list;
    }

    @FXML
    private void bRetourClick(){
        Stage stageP = (Stage) bRetour.getScene().getWindow();
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