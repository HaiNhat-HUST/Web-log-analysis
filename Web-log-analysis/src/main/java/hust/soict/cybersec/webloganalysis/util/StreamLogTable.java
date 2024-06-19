package hust.soict.cybersec.webloganalysis.util;

import hust.soict.cybersec.webloganalysis.model.LogEntry.AccessLog;
import hust.soict.cybersec.webloganalysis.model.Parser.ParseAccessLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.RandomAccessFile;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StreamLogTable {
    public static ScheduledExecutorService scheduler;
    public static String timeInterval;
    public static long prevPointer = 0;

    public static void createTable(TableView<AccessLog> streamTable) {
        // Declare columns
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

        streamTable.getColumns().addAll(typeColumn, IpColumn, timeColumn, requestColumn, statusColumn, bytesSentColumn, userColumn);
    }

    public static void setPointer() throws Exception {
        String filePath = Config.apacheLogPath;
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r");
        long length = randomAccessFile.length();
        prevPointer = length - 1;
        randomAccessFile.close();
    }

    private static void readNewLog(TableView<AccessLog> streamTable) throws Exception {
        ParseAccessLog parser = new ParseAccessLog();
        ObservableList<AccessLog> log = FXCollections.observableArrayList();
        String filePath = Config.apacheLogPath;
        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r");
        long length = randomAccessFile.length();
        if (length == 0) {
            randomAccessFile.close();
            return;
        }

        long currPointer = length - 3;
        StringBuilder lastLine = new StringBuilder();

        while (currPointer >= prevPointer) {
            randomAccessFile.seek(currPointer);
            char ch = (char) randomAccessFile.readByte();
            if (ch == '\n' || ch == '\r') {
                if (lastLine.length() != 0) {
                    log.add(parser.parse(lastLine.reverse().toString()));
                }
                lastLine = new StringBuilder();
            }
            else {
                lastLine.append(ch);
            }
            currPointer--;
        }
        if (log.size() != 0) {
            streamTable.setItems(log);
        }
        prevPointer = length - 1;
        randomAccessFile.close();
    }

    public static void startStreaming(TableView<AccessLog> streamTable) {
        Runnable functionToRun = () -> {
            try {
                readNewLog(streamTable);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        };

        scheduler.scheduleAtFixedRate(functionToRun, 0, Integer.parseInt(timeInterval), TimeUnit.MILLISECONDS);
    }

    public static void stopStreaming() {
        scheduler.shutdown();
    }
}
