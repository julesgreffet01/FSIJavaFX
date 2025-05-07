package fsiAdministration.controllers;


import javafx.fxml.FXML;

import javafx.fxml.Initializable;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;

public class AccueilController extends MenuController implements Initializable {

    @FXML
    private Label bienvenue;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setName(String name){
        name = name.toUpperCase();
        bienvenue.setText("BONJOUR " + name);
    }
}
