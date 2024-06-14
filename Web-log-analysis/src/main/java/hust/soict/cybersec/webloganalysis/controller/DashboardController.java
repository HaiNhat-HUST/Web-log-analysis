package hust.soict.cybersec.webloganalysis.controller;

import hust.soict.cybersec.webloganalysis.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class DashboardController {
    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<?> iptable;

    @FXML
    private LineChart<?, ?> linechart;

    @FXML
    private Label nameLabel;

    @FXML
    private PieChart piechart;

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<?> ruletable;

    @FXML
    private TableView<?> statustable;

    @FXML
    private Label totalVisitors;

    @FXML
    private Label uniqueVisitors;

    @FXML
    void refreshDashboard(ActionEvent event) {

    }

    @FXML
    void switchToExplorer(ActionEvent event) {
        this.mainApp.switchToExplorer();
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
