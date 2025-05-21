package fsiAdministration.controllers;

import fsiAdministration.BO.Etudiant;
import fsiAdministration.BO.Section;
import fsiAdministration.DAO.EtudiantDAO;
import fsiAdministration.DAO.SectionDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ListeEtudiantController extends MenuController implements Initializable {
    @FXML
    private TableView<Etudiant> tvEtudiants;

    @FXML
    private TableColumn<Etudiant, String> tcNomEtud;

    @FXML
    private TableColumn<Etudiant, String> tcPrenomEtud;

    @FXML
    private TableColumn<Etudiant, String> tcSection;

    @FXML
    private TableColumn<Etudiant, Void> tcModifier;

    @FXML
    private TableColumn<Etudiant, Void> tcSupprimer;

    @FXML
    private Button bRetour;

    @FXML
    private TableColumn<Etudiant, String> tcNaissance;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        SectionDAO sectionDAO = new SectionDAO();
        tcSection.setCellValueFactory(cellData -> {
            int idSec = cellData.getValue().getIdSection();
            Section sec = sectionDAO.find(idSec);
            return new SimpleStringProperty(sec != null ? sec.getLibelleSection() : "Aucune section");
        });
        tcNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaiEtu"));
        tcNomEtud.setCellValueFactory(new PropertyValueFactory<>("nomEtudiant"));
        tcPrenomEtud.setCellValueFactory(new PropertyValueFactory<>("prenomEtudiant"));
        ObservableList<Etudiant> data = getUserList();
        tvEtudiants.setItems(data);

        addButtonModifierToTable();
        addButtonSupprimerToTable();
    }

    private ObservableList<Etudiant> getUserList() {

        EtudiantDAO etudDAO = new EtudiantDAO();
        List<Etudiant> mesEtud = etudDAO.findAll();

        ObservableList<Etudiant> list = FXCollections.observableArrayList(mesEtud);
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

    private void addButtonModifierToTable() {
        tcModifier.setCellFactory(column -> new TableCell<>() {
            private final Button btn = new Button("Modifier");
            {
                btn.setOnAction(event -> {
                    Etudiant etudiant = getTableView().getItems().get(getIndex());
                    Stage stageP = (Stage) bRetour.getScene().getWindow();
                    stageP.close();

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modif-etu.fxml"));
                        Parent root = fxmlLoader.load();

                        ModifierEtudiantController modifierEtudiantController = fxmlLoader.getController();
                        modifierEtudiantController.setAttributs(etudiant);

                        Stage stage = new Stage();
                        stage.setTitle("Modification etudiant");
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

    private void addButtonSupprimerToTable() {
        tcSupprimer.setCellFactory(column -> new TableCell<>() {
            private final Button btn = new Button("Supprimer");

            {
                btn.setOnAction(event -> {
                    Etudiant etudiant = getTableView().getItems().get(getIndex());
                    tvEtudiants.getItems().remove(etudiant);
                    EtudiantDAO etudDAO = new EtudiantDAO();
                    etudDAO.delete(etudiant);
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
