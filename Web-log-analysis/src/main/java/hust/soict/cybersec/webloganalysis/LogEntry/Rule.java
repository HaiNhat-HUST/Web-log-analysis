package hust.soict.cybersec.webloganalysis.Log.LogEntry;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Rule {
    private final SimpleStringProperty triggeredRule;
    private final SimpleIntegerProperty numOfRequests;

    public Rule (String rule, int numOfRe) {
        this.triggeredRule = new SimpleStringProperty(rule);
        this.numOfRequests = new SimpleIntegerProperty(numOfRe);
    }

    public String getTriggeredRule() {
        return this.triggeredRule.get();
    }

    public int getNumOfRequests() {
        return this.numOfRequests.get();
    }

    public SimpleStringProperty triggeredRuleProperty() {
        return triggeredRule;
    }

    public SimpleIntegerProperty numOfRequestsProperty() {
        return numOfRequests;
    }
}
