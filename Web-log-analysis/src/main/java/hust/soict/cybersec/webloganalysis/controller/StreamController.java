package hust.soict.cybersec.webloganalysis.controller;

import hust.soict.cybersec.webloganalysis.Main;
import hust.soict.cybersec.webloganalysis.model.LogEntry.AccessLog;
import hust.soict.cybersec.webloganalysis.util.StreamLogTable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;

public class StreamController implements Initializable {

    private int clickCnt = 0;
    private Main mainApp;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private Label nameLabel;

    @FXML
    private Button streamButton;

    @FXML
    private TableView<AccessLog> streamTable;

    @FXML
    private ComboBox<String> timeInterval;

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

    private void setupStreamButton() {
        streamButton.setOnMouseClicked(e -> {
            if ((!e.isControlDown()) && (e.getClickCount() == 1)) {
                clickCnt += 1;
                if (clickCnt % 2 == 1) {
                    StreamLogTable.scheduler = Executors.newSingleThreadScheduledExecutor();
                    try {
                        StreamLogTable.timeInterval = timeInterval.getSelectionModel().getSelectedItem();
                        StreamLogTable.startStreaming(streamTable);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    streamButton.setText("Stop");
                }
                else{
                    StreamLogTable.stopStreaming();
                    streamButton.setText("Start");
                }
            }
        });
    }

    private void setupTimeInterval() {
        timeInterval.getItems().addAll("1",
                "5",
                "10",
                "30");
        timeInterval.setValue("5");
    }

    private void setupStreamTable() {
        StreamLogTable.createTable(streamTable);
        try {
            StreamLogTable.setPointer();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void trigger() {
        setupStreamTable();
        setupStreamButton();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTimeInterval();
    }
}
