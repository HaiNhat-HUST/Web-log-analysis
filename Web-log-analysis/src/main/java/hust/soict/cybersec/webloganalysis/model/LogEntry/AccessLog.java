package hust.soict.cybersec.webloganalysis.model.LogEntry;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AccessLog {
    private  final SimpleStringProperty type;
    private  final SimpleStringProperty IP;
    private  final SimpleStringProperty remoteIdent;
    private  final SimpleStringProperty remoteUser;
    private  final SimpleStringProperty timestamp;
    private  final SimpleStringProperty userAgent;
    private  final SimpleStringProperty requestUrl;
    private  final SimpleIntegerProperty statusCode;
    private  final SimpleIntegerProperty bytesSent;
    private  final SimpleStringProperty referer;

    public AccessLog(String ip, String remoteId, String remoteUser, String time, String userAgent, String request,
                     int statusCode, int bytesSent, String referer){
        this.type = new SimpleStringProperty("Apache Access Log");
        this.IP = new SimpleStringProperty(ip);
        this.remoteIdent = new SimpleStringProperty(remoteId);
        this.remoteUser = new SimpleStringProperty(remoteUser);
        this.timestamp = new SimpleStringProperty(time);
        this.userAgent = new SimpleStringProperty(userAgent);
        this.requestUrl = new SimpleStringProperty(request);
        this.statusCode = new SimpleIntegerProperty(statusCode);
        this.bytesSent = new SimpleIntegerProperty(bytesSent);
        this.referer = new SimpleStringProperty(referer);
    }

    public String getType(){
        return type.get();
    }

    public String getIP() {
        return IP.get();
    }

    public String getTimestamp(){
        return timestamp.get();
    }

    public String getRemoteIdent() {
        return remoteIdent.get();
    }

    public String getRemoteUser() {
        return remoteUser.get();
    }

    public String getUserAgent() {
        return userAgent.get();
    }

    public String getRequestUrl() {
        return requestUrl.get();
    }

    public int getStatusCode() {
        return statusCode.get();
    }

    public int getBytesSent() {
        return bytesSent.get();
    }

    public String getReferer() {
        return referer.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public SimpleStringProperty IPProperty() {
        return IP;
    }

    public SimpleStringProperty remoteIdentProperty() {
        return remoteIdent;
    }

    public SimpleStringProperty remoteUserProperty() {
        return remoteUser;
    }

    public SimpleStringProperty timestampProperty() {
        return timestamp;
    }

    public SimpleStringProperty userAgentProperty() {
        return userAgent;
    }

    public SimpleStringProperty requestUrlProperty() {
        return requestUrl;
    }

    public SimpleIntegerProperty statusCodeProperty() {
        return statusCode;
    }

    public SimpleIntegerProperty bytesSentProperty() {
        return bytesSent;
    }

    public SimpleStringProperty refererProperty() {
        return referer;
    }

}
