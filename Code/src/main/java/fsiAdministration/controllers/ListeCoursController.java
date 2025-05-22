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
import javafx.scene.control.TableCell;
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
    private TableColumn<Cours, String> tcDesc;

    @FXML
    private TableColumn<Cours, Void> tcModif;

    @FXML
    private TableColumn<Cours, Void> tcSupp;

    @FXML
    private Button bRetour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tcLib.setCellValueFactory(new PropertyValueFactory<>("lib"));
        tcDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        ObservableList<Cours> data = getCoursList();
        tvCours.setItems(data);
        btnModif();
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

    private void btnModif(){
        tcModif.setCellFactory(col -> new TableCell<>() {
            private final Button button = new Button("Modifier");
            {
                button.setOnAction(event -> {
                    Cours cours = getTableView().getItems().get(getIndex());
                    Stage stageP = (Stage) bRetour.getScene().getWindow();
                    stageP.close();

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_modif_cours.fxml"));
                        Parent root = fxmlLoader.load();

                        ModifierCoursController modifierCoursController = fxmlLoader.getController();
                        modifierCoursController.setAttributs(cours);
                        modifierCoursController.setName(nameUti);

                        Stage stage = new Stage();
                        stage.setTitle("Modification etudiant");
                        stage.setScene(new Scene(root));

                        stage.initModality(Modality.APPLICATION_MODAL);

                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : button);
            }
        });
    }
}