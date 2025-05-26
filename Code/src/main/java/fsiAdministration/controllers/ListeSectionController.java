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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ListeSectionController extends MenuController implements Initializable {

    @FXML
    private TableView<Section> tvSection;

    @FXML
    private TableColumn<Section, String> tcLib, tcNbEtu;

    @FXML
    private TableColumn<Section, Void> tcVp, tcModif, tcSupp;

    @FXML
    private Button bRetour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tcLib.setCellValueFactory(new PropertyValueFactory<>("libelleSection"));
        tcNbEtu.setCellValueFactory(new PropertyValueFactory<>("nbEtu"));
        ObservableList<Section> data = getSection();
        tvSection.setItems(data);
        btnModif();
        btnVp();
    }

    private ObservableList<Section> getSection() {

        SectionDAO sectionDAO = new SectionDAO();
        List<Section> mesSec = sectionDAO.findAll();
        ObservableList<Section> list = FXCollections.observableArrayList(mesSec);
        return list;
    }

    public void bRetourClick(ActionEvent actionEvent) {
        Stage stageP = (Stage) bRetour.getScene().getWindow();
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

    private void btnVp() {
        tcVp.setCellFactory(column -> new TableCell<Section, Void>() {
            private Button btn = new Button("Voir plus");
            {
                btn.setOnAction(Event -> {
                    Section section = getTableView().getItems().get(getIndex());
                    Stage stageP = (Stage) bRetour.getScene().getWindow();
                    stageP.close();

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_voir_plus_sections.fxml"));
                        Parent root = fxmlLoader.load();

                        VoirPlusSectionController voirPlusSectionController = fxmlLoader.getController();
                        voirPlusSectionController.setName(nameUti);
                        voirPlusSectionController.setIdSection(section.getIdSection());
                        voirPlusSectionController.setAttributes();

                        Stage stage = new Stage();
                        stage.setTitle("Voir plus " + section.getLibelleSection());
                        stage.setScene(new Scene(root));

                        stage.initModality(Modality.APPLICATION_MODAL);

                        stage.show();
                    }  catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

    private void btnModif() {
        tcModif.setCellFactory(column -> new TableCell<Section, Void>() {
            private Button btn = new Button("Modifier");
            {
                btn.setOnAction(event -> {
                    Section section = getTableView().getItems().get(getIndex());
                    Stage stageP = (Stage) bRetour.getScene().getWindow();
                    stageP.close();

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modif_section.fxml"));
                        Parent root = fxmlLoader.load();

                        ModifierSectionController modifierSectionController = fxmlLoader.getController();
                        modifierSectionController.setIdSec(section.getIdSection());
                        modifierSectionController.setAttrinuts();
                        modifierSectionController.setName(nameUti);

                        Stage stage = new Stage();
                        stage.setTitle("Modification section");
                        stage.setScene(new Scene(root));

                        stage.initModality(Modality.APPLICATION_MODAL);

                        stage.show();
                    }  catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : btn);
            }
        });
    }

}
