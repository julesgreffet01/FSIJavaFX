package fsiAdministration.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController {


    @FXML
    protected MenuItem bListeEtud, bAjouterEtud, bListeSection, bAjouterSection, bQuitter, bAccueil, bListeCours, bAjouterCours;

    protected String nameUti;


    @FXML
    public void bQuitterClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void bAccueilClick(ActionEvent event) {
        Stage StageE = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        StageE.close();
        try {

            // Charger le fichier FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_accueil.fxml"));
            Parent root = fxmlLoader.load();


            // Obtenir le contrôleur de la nouvelle fenetre
            AccueilController accueilController = fxmlLoader.getController();
            accueilController.setName(nameUti);
            accueilController.setBienvenue();

            // Créer une nouvelle fenêtre (Stage)
            Stage stage = new Stage();
            stage.setTitle("Page Accueil");
            stage.setScene(new Scene(root));

            // Configurer la fenêtre en tant que modal
            stage.initModality(Modality.APPLICATION_MODAL);

            // Afficher la fenêtre et attendre qu'elle se ferme
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void bListEtudClick(ActionEvent event) {
        Stage stageP = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stageP.close();
        try {

            // Charger le fichier FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_etudiant.fxml"));
            Parent root = fxmlLoader.load();


            // Obtenir le contrôleur de la nouvelle fenetre
            ListeEtudiantController listeEtudiantController = fxmlLoader.getController();
            listeEtudiantController.setName(nameUti);

            // Créer une nouvelle fenêtre (Stage)
            Stage stage = new Stage();
            stage.setTitle("Liste etudiant");
            stage.setScene(new Scene(root));

            // Configurer la fenêtre en tant que modal
            stage.initModality(Modality.APPLICATION_MODAL);

            // Afficher la fenêtre et attendre qu'elle se ferme
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void bAjouterEtudClick(ActionEvent event) {
        Stage stageP = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stageP.close();
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_ajout_etudiant.fxml"));
            Parent root = fxmlLoader.load();


            // Obtenir le contrôleur de la nouvelle fenetre
            AjouterEtudiantController ajouterEtudiantController = fxmlLoader.getController();
            ajouterEtudiantController.setName(nameUti);


            // Créer une nouvelle fenêtre (Stage)
            Stage stage = new Stage();
            stage.setTitle("ajouter un etudiant");
            stage.setScene(new Scene(root));

            // Configurer la fenêtre en tant que modal
            stage.initModality(Modality.APPLICATION_MODAL);

            // Afficher la fenêtre et attendre qu'elle se ferme
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    public void bListeSectionClick(ActionEvent event) {
        Stage stageP = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stageP.close();
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_section.fxml"));
            Parent root = fxmlLoader.load();

            ListeSectionController listeSectionController = fxmlLoader.getController();
            listeSectionController.setName(nameUti);

            Stage stage = new Stage();
            stage.setTitle("Liste etudiant");
            stage.setScene(new Scene(root));

            stage.initModality(Modality.APPLICATION_MODAL);

            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void bAjouterSectionClick(ActionEvent event) {}

    @FXML
    public void bListeCoursClick(ActionEvent event) {
        Stage stageP = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
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
            stage.setTitle("Liste etudiant");
            stage.setScene(new Scene(root));

            // Configurer la fenêtre en tant que modal
            stage.initModality(Modality.APPLICATION_MODAL);

            // Afficher la fenêtre et attendre qu'elle se ferme
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setName(String nameUti) {
        this.nameUti = nameUti;
    }

    @FXML
    public void bAjouterCoursClick(ActionEvent event) {
        Stage stageP = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stageP.close();
        try {

            // Charger le fichier FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_ajout_cours.fxml"));
            Parent root = fxmlLoader.load();


            // Obtenir le contrôleur de la nouvelle fenetre
            AjouterCoursController ajouterCoursController = fxmlLoader.getController();
            ajouterCoursController.setName(nameUti);

            // Créer une nouvelle fenêtre (Stage)
            Stage stage = new Stage();
            stage.setTitle("Liste etudiant");
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
