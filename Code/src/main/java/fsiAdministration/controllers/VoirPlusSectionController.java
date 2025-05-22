package fsiAdministration.controllers;

import fsiAdministration.BO.Cours;
import fsiAdministration.BO.Etudiant;
import fsiAdministration.DAO.CoursDAO;
import fsiAdministration.DAO.EtudiantDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class VoirPlusSectionController extends MenuController implements Initializable {

    @FXML
    private TableView<Cours> tvCours;

    @FXML
    private TableColumn<Cours, String> tcLib;

    @FXML
    private TableView<Etudiant> tvEtu;

    @FXML
    private TableColumn<Etudiant, String> tcPre, tcNom;

    @FXML
    private Button bRetour;

    private int idSection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setAttributes(){
        tcNom.setCellValueFactory(new PropertyValueFactory<>("nomEtudiant"));
        tcPre.setCellValueFactory(new PropertyValueFactory<>("prenomEtudiant"));
        tvEtu.setItems(getUserList());

        tcLib.setCellValueFactory(new PropertyValueFactory<>("lib"));
        tvCours.setItems(getCoursList());
    }

    private ObservableList<Etudiant> getUserList() {

        EtudiantDAO etudDAO = new EtudiantDAO();
        List<Etudiant> mesEtud = etudDAO.getAllBySection(this.idSection);

        ObservableList<Etudiant> list = FXCollections.observableArrayList(mesEtud);
        System.out.println(mesEtud);
        return list;
    }

    private ObservableList<Cours> getCoursList() {
        CoursDAO coursDAO = new CoursDAO();
        List<Cours> mesCours = coursDAO.getAllBySection(this.idSection);

        ObservableList<Cours> list = FXCollections.observableArrayList(mesCours);
        System.out.println(mesCours);
        return list;
    }

    public void bRetourClick(ActionEvent actionEvent) {
        Stage stageP = (Stage) bRetour.getScene().getWindow();
        stageP.close();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fsiAdministration/views/page_liste_section.fxml"));
            Parent root = fxmlLoader.load();

            ListeSectionController listeSectionController = fxmlLoader.getController();
            listeSectionController.setName(nameUti);

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

    public void setIdSection(int idSection) {
        this.idSection = idSection;
    }
}
