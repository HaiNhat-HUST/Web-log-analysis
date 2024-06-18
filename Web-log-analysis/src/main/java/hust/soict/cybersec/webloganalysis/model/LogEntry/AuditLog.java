package hust.soict.cybersec.webloganalysis.model.LogEntry;

import javafx.beans.property.SimpleStringProperty;

public class AuditLog {
    private final SimpleStringProperty type;
    private final SimpleStringProperty timestamp;
    private final SimpleStringProperty remoteAddress;
    private final SimpleStringProperty request;
    private final SimpleStringProperty triggeredRule;

    public AuditLog(String timestamp, String remoteAddress, String request, String triggeredRule) {
        this.type = new SimpleStringProperty("Modsecurity Audit Log");
        this.timestamp = new SimpleStringProperty(timestamp);
        this.remoteAddress = new SimpleStringProperty(remoteAddress);
        this.request = new SimpleStringProperty(request);
        this.triggeredRule = new SimpleStringProperty(triggeredRule);
    }

    public String getType(){
        return type.get();
    }

    public String getTimestamp(){
        return this.timestamp.get();
    }

    public String getRemoteAddress(){
        return this.remoteAddress.get();
    }

    public String getRequest(){
        return this.request.get();
    }

    public String getTriggeredRule(){
        return this.triggeredRule.get();
    }

    public String toString() {
        return this.remoteAddress + " " + this.timestamp + " " + this.request + " " + this.triggeredRule;
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public SimpleStringProperty timestampProperty() {
        return timestamp;
    }

    public SimpleStringProperty remoteAddressProperty() {
        return remoteAddress;
    }

    public SimpleStringProperty requestProperty() {
        return request;
    }

    public SimpleStringProperty triggeredRuleProperty() {
        return triggeredRule;
    }
}
