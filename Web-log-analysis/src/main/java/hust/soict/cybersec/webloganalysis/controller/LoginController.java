package hust.soict.cybersec.webloganalysis.controller;

import hust.soict.cybersec.webloganalysis.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private TextField password;

    @FXML
    private ComboBox<?> profileCb;

    @FXML
    private ImageView profileChoices;

    @FXML
    void addNewProfile(ActionEvent event) {
        this.mainApp.switchToRegister();
    }

    @FXML
    void start(ActionEvent event) {
        this.mainApp.switchToWelcome();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
