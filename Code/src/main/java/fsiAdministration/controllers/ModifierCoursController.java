package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.SectionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    }

    @FXML
    public void bEnregistrerClick(ActionEvent event) {

    }

    @FXML
    private void bRetourClick(ActionEvent event) {

    }

}
