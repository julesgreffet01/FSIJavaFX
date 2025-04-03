package fsiAdministration.controllers;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.EtudiantDAO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AjouterEtudiantController extends MenuController implements Initializable {

    @FXML
    private TextField tfNomEtud, tfPrenomEtud;
    @FXML
    private Button bRetour;
    @FXML
    private ListView<Section> lvSectionEtud ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<Section> sections = getSectionList();

        ObservableList<Section> section = FXCollections.observableArrayList(sections);
        lvSectionEtud.setItems(section);
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

        String x = tfNomEtud.getText();
        String y = tfPrenomEtud.getText();
        Section selected = lvSectionEtud.getSelectionModel().getSelectedItem();
        int z = selected.getIdSection();

        Etudiant newEtud = new Etudiant(0,x,y,z);

        System.out.println(newEtud);
        EtudiantDAO etudDAO = new EtudiantDAO();
        boolean controle = etudDAO.create(newEtud);
        if(controle) {
            tfNomEtud.clear();
            tfPrenomEtud.clear();
            lvSectionEtud.getSelectionModel().clearSelection();
        }
    }

    @FXML
    public void bEffacerClick(ActionEvent event) {
        tfNomEtud.clear();
        tfPrenomEtud.clear();
        lvSectionEtud.getSelectionModel().clearSelection();
    }
}
