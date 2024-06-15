package hust.soict.cybersec.webloganalysis.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import hust.soict.cybersec.webloganalysis.model.LogEntry.Rule;
import hust.soict.cybersec.webloganalysis.Log.Parser.ParseAuditLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class RuleTable {

    public static void creatTable(TableView<Rule> ruleTable) {
        // Declare columns
        TableColumn<Rule, String> ruleColumn = new TableColumn<Rule, String>("Triggered Rule");
        TableColumn<Rule, Integer> freColumn = new TableColumn<Rule, Integer>("Number of Requests");

        // Set cell
        ruleColumn.setCellValueFactory(cellData -> cellData.getValue().triggeredRuleProperty());
        freColumn.setCellValueFactory(cellData -> cellData.getValue().numOfRequestsProperty().asObject());

        ruleTable.getColumns().addAll(ruleColumn, freColumn);

    }

    public static void addData(TableView<Rule> ruleTable, String dateTarget) {
        String filePath = "";
        ObservableList<Rule> rules = FXCollections.observableArrayList();
        HashMap<String, Integer> hm  = new HashMap<>();
        try {
            ParseAuditLog parser = new ParseAuditLog();
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (parser.getDate(line).equals(dateTarget)) {
                    String rule = parser.getTriggeredRule(line);
                    if (hm.containsKey(rule)) {
                        hm.put(rule, hm.get(rule) + 1);
                    }
                    else {
                        hm.put(rule, 1);
                    }
                }
            }
            bufferedReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for(String rule : hm.keySet()) {
            rules.add(new Rule(rule, hm.get(rule)));
        }

        ruleTable.setItems(rules);
    }
}
