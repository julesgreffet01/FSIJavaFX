package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.CoursDAO;
import fsiAdministration.DAO.SectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterCoursController extends MenuController implements Initializable {

    @FXML
    private TextField tfLibCours, tfDescCours,tfVoluCours;

    @FXML
    private Button bRetour, bEnregistrer, bEffacer;

    @FXML
    private ListView<Section> lvSectionCours;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Section> sections = getSectionList();

        lvSectionCours.setItems(sections);
    }

    private ObservableList<Section> getSectionList() {

        SectionDAO sectionDAO = new SectionDAO();
        List<Section> sections = sectionDAO.findAll();

        ObservableList<Section> list = FXCollections.observableArrayList(sections);
        return list;
    }
    @FXML
    public void bRetourClick(ActionEvent event) {
        // On fait le lien avec l'ecran actuelle
        Stage stageP = (Stage) bRetour.getScene().getWindow();
        //on ferme l'écran
        stageP.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
            Parent root = fxmlLoader.load();

            AccueilController accueilController = fxmlLoader.getController();
            accueilController.setName(nameUti);
            accueilController.setBienvenue();

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

    @FXML
    public void bEnregistrerClick(ActionEvent event) {
        String lib = tfLibCours.getText();
        String desc = tfDescCours.getText();
        String vol = tfVoluCours.getText();
        Section selected = lvSectionCours.getSelectionModel().getSelectedItem();
        if(lib != null && desc != null && !lib.trim().isEmpty() && !desc.trim().isEmpty() && selected != null) {
            int idSection = selected.getIdSection();
            int volumeHoraire = Integer.parseInt(vol);
            Cours newCours = new Cours(0, lib, desc, idSection,0,volumeHoraire);
            CoursDAO coursDAO = new CoursDAO();
            boolean controle = coursDAO.create(newCours);
            if(controle) {
                tfLibCours.clear();
                tfDescCours.clear();
                lvSectionCours.getSelectionModel().clearSelection();
            }
        } else {
            try {
                // Charger le fichier FXML
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/popup_ajout_etu.fxml"));
                Parent root = fxmlLoader.load();


                // Obtenir le contrôleur de la nouvelle fenetre
                PopupEtuController popupEtuController = fxmlLoader.getController();

                // Créer une nouvelle fenêtre (Stage)
                Stage stage = new Stage();
                stage.setTitle("Pop-up");
                stage.setScene(new Scene(root));

                // Configurer la fenêtre en tant que modal
                stage.initModality(Modality.APPLICATION_MODAL);

                // Afficher la fenêtre et attendre qu'elle se ferme
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void bEffacerClick(ActionEvent event) {
        tfLibCours.clear();
        tfDescCours.clear();
        lvSectionCours.getSelectionModel().clearSelection();
    }
}
