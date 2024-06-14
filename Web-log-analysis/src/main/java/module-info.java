module hust.soict.cybersec.webloganalysis {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens hust.soict.cybersec.webloganalysis to javafx.fxml;
    exports hust.soict.cybersec.webloganalysis;
    opens hust.soict.cybersec.webloganalysis.controller;
    exports hust.soict.cybersec.webloganalysis.controller to javafx.fxml;

}