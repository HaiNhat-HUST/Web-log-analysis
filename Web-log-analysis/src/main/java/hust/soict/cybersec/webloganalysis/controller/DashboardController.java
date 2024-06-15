package hust.soict.cybersec.webloganalysis.controller;

import hust.soict.cybersec.webloganalysis.Main;
import hust.soict.cybersec.webloganalysis.util.RuleTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import hust.soict.cybersec.webloganalysis.model.LogEntry.Rule;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
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
    private TableView<Rule> ruletable;

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

    private  void setupDatePicker() {
        datePicker.setValue(LocalDate.now());
    }

    private void setupRuleTable() {
        RuleTable.creatTable(ruletable);
        RuleTable.addData(ruletable, datePicker.getValue().toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupDatePicker();
        setupRuleTable();
    }
    
}
