package hust.soict.cybersec.webloganalysis.model.LogEntry;

import java.io.FileInputStream;
import java.io.InputStream;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class IpAddress {
    private final SimpleStringProperty ip;
    private final SimpleIntegerProperty numberOfRequests;
    private final SimpleStringProperty countryName;

    public IpAddress(String ip, int requests, String countryName){
        this.ip = new SimpleStringProperty(ip);
        this.numberOfRequests = new SimpleIntegerProperty(requests);
        this.countryName = new SimpleStringProperty(countryName);
    }


    public String getCountryName(){
        return this.countryName.get();
    }

    public String getIp(){
        return this.ip.get();
    }

    public int getNumberOfRequests(){
        return this.numberOfRequests.get();
    }

    public SimpleStringProperty ipProperty() {
        return ip;
    }

    public SimpleIntegerProperty numberOfRequestsProperty() {
        return numberOfRequests;
    }

    public SimpleStringProperty countryNameProperty() {
        return countryName;
    }
}
