package hust.soict.cybersec.webloganalysis.controller;

import hust.soict.cybersec.webloganalysis.Main;
import hust.soict.cybersec.webloganalysis.model.LogEntry.AccessLog;
import hust.soict.cybersec.webloganalysis.model.LogEntry.AuditLog;
import hust.soict.cybersec.webloganalysis.util.AccessLogTable;
import hust.soict.cybersec.webloganalysis.util.AuditLogTable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ExplorerController implements Initializable {

    private Main mainApp;

    @FXML
    private TableView<AccessLog> apacheTable;

    @FXML
    private TableView<AuditLog> modsecTable;

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

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    private void changeTable(int index) {
        SingleSelectionModel<Tab> selectionModel = tablePane.getSelectionModel();
        selectionModel.select(index);
    }

    public void applyFilter(String typeOfFilter, String typeOfTable) {
        if (typeOfTable.equals("Apache")) {
            if (typeOfFilter.equals("")){
                return;
            }
            changeTable(0);
            AccessLogTable.addData(apacheTable, typeOfFilter);
        }
        else{
            changeTable(1);
            AuditLogTable.addData(modsecTable);
        }
    }

    public void refresh(ActionEvent event) {
        apacheTable.setItems(FXCollections.observableArrayList());
        modsecTable.setItems(FXCollections.observableArrayList());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AccessLogTable.createTable(apacheTable);
        AuditLogTable.createTable(modsecTable);
    }
}
