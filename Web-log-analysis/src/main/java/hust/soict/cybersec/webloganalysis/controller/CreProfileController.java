package hust.soict.cybersec.webloganalysis.controller;

import hust.soict.cybersec.webloganalysis.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreProfileController {
    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private TextField apacheLogPath;

    @FXML
    private TextField modsecLogPath;

    @FXML
    private PasswordField password;

    @FXML
    private TextField profileName;

    @FXML
    void apacheBrowse(ActionEvent event) {

    }

    @FXML
    void backToLogin(ActionEvent event) {
        this.mainApp.switchToLogin();
    }

    @FXML
    void modsecBrowse(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) {

    }

}
