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
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ModifierEtudiantController extends MenuController implements Initializable {

    @FXML
    private TextField tfNomEtud, tfPrenomEtud;
    @FXML
    private Button bRetour;
    @FXML
    private ListView<Section> lvSectionEtud ;

    @FXML
    private DatePicker datePickerNai;

    private int idEtu;


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

    public void setAttributs(Etudiant etu) {
        tfNomEtud.setText(etu.getNomEtudiant());
        tfPrenomEtud.setText(etu.getPrenomEtudiant());
        if(etu.getDateNaiEtu() != null){
            datePickerNai.setValue(etu.getDateNaiEtu().toLocalDate());
        }
        lvSectionEtud.getSelectionModel().select(etu.getIdSection() - 1);
        this.idEtu = etu.getIdEtudiant();
    }

    @FXML
    private void bEnregistrerClick(ActionEvent event) {
        String x = tfNomEtud.getText();
        String y = tfPrenomEtud.getText();
        Section selected = lvSectionEtud.getSelectionModel().getSelectedItem();
        LocalDate dateNai = datePickerNai.getValue();

        if(x != null && y != null && selected != null && dateNai != null) {
            int z = selected.getIdSection();
            Date sqlDate = Date.valueOf(dateNai);
            Etudiant newEtud = new Etudiant(idEtu,x,y,z, sqlDate);
            System.out.println(newEtud.getNomEtudiant());

            EtudiantDAO etudDAO = new EtudiantDAO();
            boolean controle = etudDAO.update(newEtud);
            if(controle) {
                tfNomEtud.clear();
                tfPrenomEtud.clear();
                lvSectionEtud.getSelectionModel().clearSelection();
                datePickerNai.setValue(null);
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_etudiant.fxml"));
            Parent root = fxmlLoader.load();


            // Obtenir le contrôleur de la nouvelle fenetre
            ListeEtudiantController listeEtudiantController = fxmlLoader.getController();

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
