package hust.soict.cybersec.webloganalysis.controller;

import hust.soict.cybersec.webloganalysis.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WelcomeController {
    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void switchToDashboard(ActionEvent event) {
        this.mainApp.switchToDashboard();
    }

    @FXML
    void switchToExplorer(ActionEvent event) {
        this.mainApp.switchToExplorer();
    }

    @FXML
    void switchToStream(ActionEvent event) {
        this.mainApp.switchToStream();
    }
}
