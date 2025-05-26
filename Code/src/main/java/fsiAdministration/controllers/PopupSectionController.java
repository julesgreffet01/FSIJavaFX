package fsiAdministration.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PopupSectionController implements Initializable {
    @FXML
    private Button bPopupSec;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void bPopupSecClick(ActionEvent event) {
        Stage stageP = (Stage) bPopupSec.getScene().getWindow();
        stageP.close();
    }

}
