package hust.soict.cybersec.webloganalysis.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import hust.soict.cybersec.webloganalysis.model.LogEntry.AuditLog;
import hust.soict.cybersec.webloganalysis.model.Parser.ParseAuditLog;

public class AuditLogTable {

    public static String dateTarget;
    public static String ruleTarget;

    @SuppressWarnings("unchecked")
    public static void createTable(TableView<AuditLog> modsecTable) {
        // Declare each columns
        TableColumn<AuditLog, String> typeColumn = new TableColumn<>("Type");
        TableColumn<AuditLog, String> remoteAddColumn = new TableColumn<>("Remote Address");
        TableColumn<AuditLog, String> timeColumn = new TableColumn<>("Timestamp");
        TableColumn<AuditLog, String> requestColumn = new TableColumn<>("Request");
        TableColumn<AuditLog, String> ruleColumn = new TableColumn<>("Triggered Rule");

        // Set cell
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        remoteAddColumn.setCellValueFactory(cellData -> cellData.getValue().remoteAddressProperty());
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timestampProperty());
        requestColumn.setCellValueFactory(cellData -> cellData.getValue().requestProperty());
        ruleColumn.setCellValueFactory(cellData -> cellData.getValue().triggeredRuleProperty());

        modsecTable.getColumns().addAll(typeColumn, remoteAddColumn, timeColumn, requestColumn, ruleColumn);
    }

    public static void addData(TableView<AuditLog> modsecTable) {
        String filePath = Config.modsecLogPath;
        ObservableList<AuditLog> logs = FXCollections.observableArrayList();
        try {
			ParseAuditLog parser = new ParseAuditLog();
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
				if (parser.getDate(line).equals(dateTarget)) {
					if (parser.getTriggeredRule(line).equals(ruleTarget)) {
                        logs.add(parser.parse(line));
                    }
				}         
            }
			modsecTable.setItems(logs);;
			bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
