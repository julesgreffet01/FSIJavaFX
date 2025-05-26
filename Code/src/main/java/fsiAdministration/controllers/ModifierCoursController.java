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

public class ModifierCoursController extends MenuController implements Initializable {

    @FXML
    private TextField tfLibCours, tfDescCours;

    @FXML
    private Button bRetour, bEnregistrer;

    @FXML
    private ListView<Section> lvSectionCours;
    private Integer idCours;

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

    public void setAttributs(Cours cours) {
        tfLibCours.setText(cours.getLib());
        tfDescCours.setText(cours.getDesc());
        lvSectionCours.getSelectionModel().select(cours.getIdSection() - 1);
        this.idCours = cours.getIdSection();
    }

    @FXML
    public void bEnregistrerClick(ActionEvent event) {
        String lib = tfLibCours.getText();
        String desc = tfDescCours.getText();
        Section selected = lvSectionCours.getSelectionModel().getSelectedItem();
        if(lib != null && desc != null && !lib.trim().isEmpty() && !desc.trim().isEmpty() && selected != null) {
            int idSection = selected.getIdSection();
            Cours newCours = new Cours(idCours, lib, desc, idSection);

            CoursDAO coursDAO = new CoursDAO();
            boolean controle = coursDAO.update(newCours);
            if(controle) {
                Stage stageP = (Stage) bRetour.getScene().getWindow();
                stageP.close();
                try {

                    // Charger le fichier FXML
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_cours.fxml"));
                    Parent root = fxmlLoader.load();


                    // Obtenir le contrôleur de la nouvelle fenetre
                    ListeCoursController listeCoursController = fxmlLoader.getController();
                    listeCoursController.setName(nameUti);

                    // Créer une nouvelle fenêtre (Stage)
                    Stage stage = new Stage();
                    stage.setTitle("Liste cours");
                    stage.setScene(new Scene(root));

                    // Configurer la fenêtre en tant que modal
                    stage.initModality(Modality.APPLICATION_MODAL);

                    // Afficher la fenêtre et attendre qu'elle se ferme
                    stage.show();


                } catch (Exception e) {
                    e.printStackTrace();
                }
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
    private void bRetourClick(ActionEvent event) {
        Stage stageP = (Stage) bRetour.getScene().getWindow();
        stageP.close();
        try {

            // Charger le fichier FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_cours.fxml"));
            Parent root = fxmlLoader.load();


            // Obtenir le contrôleur de la nouvelle fenetre
            ListeCoursController listeCoursController = fxmlLoader.getController();
            listeCoursController.setName(nameUti);

            // Créer une nouvelle fenêtre (Stage)
            Stage stage = new Stage();
            stage.setTitle("Liste cours");
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
