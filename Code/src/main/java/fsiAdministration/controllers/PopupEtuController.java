package fsiAdministration.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class PopupEtuController implements Initializable {

    @FXML
    private Button bPopupEtu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void bPopupEtuClick(ActionEvent event) {
        Stage stageP = (Stage) bPopupEtu.getScene().getWindow();
        stageP.close();
    }
}
