package hust.soict.cybersec.webloganalysis.controller;

import hust.soict.cybersec.webloganalysis.Main;
import hust.soict.cybersec.webloganalysis.util.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private Main mainApp;

    @FXML
    private TextField password;

    @FXML
    private ComboBox<String> profileCb;

    @FXML
    private ImageView profileChoices;

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void addNewProfile(ActionEvent event) {
        this.mainApp.switchToCreProfile();
    }


    @FXML
    void start(ActionEvent event) {
        if (profileCb.getItems().isEmpty() || password.getText().isEmpty()) {
            return;
        }
        if (Config.checkProfile(password.getText(), profileCb.getSelectionModel().getSelectedItem())) {
            mainApp.switchToWelcome();
        }
    }

    @FXML
    void logInAsVisitor(ActionEvent event) {
        Config.logInAsVisitor();
        this.mainApp.switchToWelcome();
    }
    
    private void setupProfileCb() {
        Config.loadProfile(profileCb);
    }
  

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupProfileCb();
    }
}
