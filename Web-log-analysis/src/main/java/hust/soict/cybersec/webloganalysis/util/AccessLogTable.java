package hust.soict.cybersec.webloganalysis.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

import hust.soict.cybersec.webloganalysis.model.LogEntry.AccessLog;
import hust.soict.cybersec.webloganalysis.model.Parser.ParseAccessLog;

public class AccessLogTable{

	public static String dateTarget;
	public static String ipTarget;
	public static int statusTarget;
	public static int hourTarget;

    @SuppressWarnings("unchecked")
	public static void createTable(TableView<AccessLog> logTable){
		// Declare each column
		TableColumn<AccessLog, String> typeColumn = new TableColumn<>("Type");
		TableColumn<AccessLog, String> IpColumn = new TableColumn<>("IP");
		TableColumn<AccessLog, String> timeColumn = new TableColumn<>("Timestamp");
		TableColumn<AccessLog, String> requestColumn = new TableColumn<>("Request Message");
		TableColumn<AccessLog, Integer> statusColumn = new TableColumn<>("Status");
		TableColumn<AccessLog, Integer> bytesSentColumn = new TableColumn<>("Bytes");
		TableColumn<AccessLog, String> userColumn = new TableColumn<>("User Agent");

		// Set cell 
		typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
		IpColumn.setCellValueFactory(cellData -> cellData.getValue().IPProperty());
		timeColumn.setCellValueFactory(cellData -> cellData.getValue().timestampProperty());
		userColumn.setCellValueFactory(cellData -> cellData.getValue().userAgentProperty());
		requestColumn.setCellValueFactory(cellData -> cellData.getValue().requestUrlProperty());
		statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusCodeProperty().asObject());
		bytesSentColumn.setCellValueFactory(cellData -> cellData.getValue().bytesSentProperty().asObject());

		// Format each column
		IpColumn.setMinWidth(10);
		IpColumn.setMaxWidth(500);

		timeColumn.setMinWidth(10);
		timeColumn.setMaxWidth(500);

		requestColumn.setMinWidth(10);
		requestColumn.setMaxWidth(250);

		statusColumn.setMinWidth(10);
		statusColumn.setMaxWidth(50);

		bytesSentColumn.setMinWidth(10);
		bytesSentColumn.setMaxWidth(50);

		logTable.getColumns().addAll(typeColumn, IpColumn, timeColumn, requestColumn, statusColumn, bytesSentColumn, userColumn);
	}
	
	public static void  addData(TableView<AccessLog> logTable, String typeOfFilter){
		String filePath = Config.apacheLogPath;
		ObservableList<AccessLog> logs = FXCollections.observableArrayList();
        try {
			ParseAccessLog parser = new ParseAccessLog();
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
				if (parser.getDate(line).equals(dateTarget)) {
					if (typeOfFilter.equals("")) {
						logs.add(parser.parse(line)); 
					}
					else if ((typeOfFilter.equals("Hour")) && (parser.getHour(line) == hourTarget)) {
						logs.add(parser.parse(line)); 
					}
					else if ((typeOfFilter.equals("IP")) && (parser.getIp(line).equals(ipTarget))) {
						logs.add(parser.parse(line));  
					}
					else if ((typeOfFilter.equals("Status")) && (parser.getStatusCode(line) == statusTarget)) {
						logs.add(parser.parse(line)); 
					} 
				}         
            }
			logTable.setItems(logs);;
			bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
