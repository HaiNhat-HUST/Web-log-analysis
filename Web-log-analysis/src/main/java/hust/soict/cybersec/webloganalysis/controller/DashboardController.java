package hust.soict.cybersec.webloganalysis.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import hust.soict.cybersec.webloganalysis.model.LogEntry.Rule;
import hust.soict.cybersec.webloganalysis.Main;
import hust.soict.cybersec.webloganalysis.util.RuleTable;
import hust.soict.cybersec.webloganalysis.model.LogEntry.IpAddress;
import hust.soict.cybersec.webloganalysis.util.IpAddressToCountryName;

public class DashboardController implements Initializable {
    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<IpAdddress> iptable;

    @FXML
    private LineChart<String, Number> linechart;

    @FXML
    private Label nameLabel;

    @FXML
    private PieChart piechart;

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<Rule> ruletable;

    @FXML
    private TableView<StatusCode> statustable;

    @FXML
    private Label totalVisitors;

    @FXML
    private Label uniqueVisitors;

    @FXML
    void refreshDashboard(ActionEvent event) {
        String targetDate = datePicker.getValue().toString();
		AccessLogTable.dateTarget = targetDate;
		AuditLogTable.dateTarget = targetDate;

		ruletable.setItems(FXCollections.observableArrayList());
		iptable.setItems(FXCollections.observableArrayList());
		piechart.setData(FXCollections.observableArrayList());
		statustable.setItems(FXCollections.observableArrayList());
		linechart.getData().clear();

		IpAddressToCountryName.addData(iptable, piechart, targetDate);
		totalVisitors.setText(Integer.toString(IpAddressToCountryName.totalVisitors));
		uniqueVisitors.setText(Integer.toString(IpAddressToCountryName.uniqueVisitors));

		LogLineChart.addData(linechart, targetDate);
		setupActionToLineChart();
		StatusCodeTable.addData(statustable, targetDate);
		ModsecRuleTable.addData(ruletable, targetDate);
    }

    @FXML
    void switchToExplorer(ActionEvent event) {
        this.mainApp.switchToExplorer("", "");
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

    private void setupIpTableAndPieChart() {
        IpAddressToCountryName.creatIpTable(iptable);
        IpAddressToCountryName.addData(iptable, piechart, datePicker.getValue().toString());
		iptable.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				if (iptable.getSelectionModel().getSelectedItem() != null){
					AccessLogTable.ipTarget = iptable.getSelectionModel().getSelectedItem().getIp();
					mainApp.switchToExplorer("IP", "Apache");
				}
			}
		});
        totalVisitors.setText(Integer.toString(IpAddressToCountryName.totalVisitors));
		uniqueVisitors.setText(Integer.toString(IpAddressToCountryName.uniqueVisitors));
    }

    private void setupLineChart() {
        linechart.setTitle("Log Line Chart");
		LogLineChart.addData(linechart,  datePicker.getValue().toString());
		setupActionToLineChart();
    }

    private void setupActionToLineChart() {
        ObservableList<LineChart.Series<String, Number>> seriesList = linechart.getData();
		for(Series<String, Number> series : seriesList){
			ObservableList<XYChart.Data<String, Number>> datas = series.getData();
			for (XYChart.Data<String, Number> data : datas){
				data.getNode().setOnMouseClicked(e -> {
					if ((!e.isControlDown()) && (e.getClickCount() == 2)) {
						AccessLogTable.hourTarget = Integer.parseInt(data.getXValue());
						mainApp.switchToExplorer("Hour", "Apache");
					}
				});
			}
		}
    }

    private void setupStatusTable() {
        StatusCodeTable.createTable(statustable);
		StatusCodeTable.addData(statustable,datePicker.getValue().toString());
		statustable.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2) {
				if (statustable.getSelectionModel().getSelectedItem() != null){
					AccessLogTable.statusTarget = statustable.getSelectionModel().getSelectedItem().getStatusCode();
					mainApp.switchToExplorer("Status", "Apache");
				}
			}
		});
    }
    
    public void trigger() {
        setupDatePicker();
        setupRuleTable();
        setupIpTableAndPieChart();
        setupLineChart();
        setupStatusTable();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
    
}
