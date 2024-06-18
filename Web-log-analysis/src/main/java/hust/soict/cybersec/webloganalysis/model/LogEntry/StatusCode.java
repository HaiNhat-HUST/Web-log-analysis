package hust.soict.cybersec.webloganalysis.model.LogEntry;

import javafx.beans.property.SimpleIntegerProperty;

public class StatusCode {
    private final SimpleIntegerProperty statusCode;
    private final SimpleIntegerProperty numberOfRequests;

    public StatusCode(int statusCode, int requests){
        this.statusCode = new SimpleIntegerProperty(statusCode);
        this.numberOfRequests = new SimpleIntegerProperty(requests);
    }

    public int getStatusCode(){
        return this.statusCode.get();
    }

    public int getNumberOfRequests(){
        return this.numberOfRequests.get();
    }

    public SimpleIntegerProperty statusCodeProperty() {
        return statusCode;
    }

    public SimpleIntegerProperty numberOfRequestsProperty() {
        return numberOfRequests;
    }
}
