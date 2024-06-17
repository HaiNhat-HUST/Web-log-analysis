package hust.soict.cybersec.webloganalysis.controller;

import com.google.gson.JsonObject;
import hust.soict.cybersec.webloganalysis.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class CreProfileController  {
    private Main mainApp;

    @FXML
    private TextField apacheLogPath;

    @FXML
    private TextField modsecLogPath;

    @FXML
    private PasswordField password;

    @FXML
    private TextField profileName;



    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    void apacheBrowse(ActionEvent event) {
        apacheLogPath.setText("");
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Select the log source");

        File resourceDir = new File("src/main/resources/hust/soict/cybersec/webloganalysis/log_demo");
        filechooser.setInitialDirectory(resourceDir);
        java.io.File selectedFile = filechooser.showOpenDialog(stage);

        if (selectedFile != null) {
            apacheLogPath.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    void modsecBrowse(ActionEvent event) {
        modsecLogPath.setText("");
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Select the log source");

        File resourceDir = new File("src/main/resources/hust/soict/cybersec/webloganalysis/log_demo");
        filechooser.setInitialDirectory(resourceDir);
        java.io.File selectedFile = filechooser.showOpenDialog(stage);

        if (selectedFile != null) {
            modsecLogPath.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    void backToLogin(ActionEvent event) {
        this.mainApp.switchToLogin();
    }

    @FXML
    void submit(ActionEvent event) {
        //file is not config ???
        createNewProfile();
        mainApp.switchToWelcome();
    }

    private void createNewProfile() {
        JsonObject newProfile = new JsonObject();
        newProfile.addProperty("profileName", profileName.getText());
        newProfile.addProperty("password", password.getText());
        newProfile.addProperty("apacheLogPath", apacheLogPath.getText());
        newProfile.addProperty("modsecLogPath", modsecLogPath.getText());
        Config.createNewProfile(newProfile);
    }

}
