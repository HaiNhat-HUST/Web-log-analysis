module hust.soict.cybersec.webloganalysis {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    
    opens hust.soict.cybersec.webloganalysis to javafx.fxml;
    exports hust.soict.cybersec.webloganalysis;
    opens hust.soict.cybersec.webloganalysis.controller;
    exports hust.soict.cybersec.webloganalysis.controller to javafx.fxml;

}
