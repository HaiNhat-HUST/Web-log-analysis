package hust.soict.cybersec.webloganalysis.controller;

import hust.soict.cybersec.webloganalysis.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ExplorerController {
    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private TableView<?> apacheTable;

    @FXML
    private TableView<?> modsecTable;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField searching;

    @FXML
    private TabPane tablePane;

    @FXML
    void switchToDashboard(ActionEvent event) {
        this.mainApp.switchToDashboard();
    }

    @FXML
    void switchToStream(ActionEvent event) {
        this.mainApp.switchToStream();
    }

    @FXML
    void switchToWelcome(ActionEvent event) {
        this.mainApp.switchToWelcome();
    }
}
