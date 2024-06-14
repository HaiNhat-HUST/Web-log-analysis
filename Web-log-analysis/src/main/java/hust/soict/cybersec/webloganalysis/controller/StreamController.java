package hust.soict.cybersec.webloganalysis.controller;

import hust.soict.cybersec.webloganalysis.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class StreamController {
    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private Label nameLabel;

    @FXML
    private Button streamButton;

    @FXML
    private TableView<?> streamTable;

    @FXML
    private ComboBox<?> timeInterval;

    @FXML
    void switchToDashboard(ActionEvent event) {
        this.mainApp.switchToDashboard();
    }

    @FXML
    void switchToExplorer(ActionEvent event) {
        this.mainApp.switchToExplorer();
    }

    @FXML
    void switchToWelcome(ActionEvent event) {
        this.mainApp.switchToWelcome();
    }
}
