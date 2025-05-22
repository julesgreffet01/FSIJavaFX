package fsiAdministration.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorController implements Initializable {
    @FXML
    private Button ButtonOk;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void ButtonOkOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) ButtonOk.getScene().getWindow();
        stage.close();

    }
}
