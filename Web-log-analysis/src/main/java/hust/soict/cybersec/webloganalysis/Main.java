package hust.soict.cybersec.webloganalysis;

import hust.soict.cybersec.webloganalysis.controller.*;
import hust.soict.cybersec.webloganalysis.util.Config;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main  extends Application {

    private Stage primaryStage;
    private Scene login,creProfile , welcome, stream, explorer, dashboard;
    private CreProfileController creProfileController;
    private LoginController loginController;
    private WelcomeController welcomeController;
    private StreamController streamController;
    private DashboardController dashboardController;
    private ExplorerController explorerController;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Config.currentPath = System.getProperty("user.dir");
        String filePath = System.getProperty("user.dir") + File.separator + "profile.txt";
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            try {
                Path newFile = Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            this.primaryStage = primaryStage;
            primaryStage.setTitle("Service Log Analyze");

            InputStream input = getClass().getResourceAsStream("/hust/soict/cybersec/webloganalysis/image/detective.png");
            Image appicon = new Image(input);

            primaryStage.getIcons().add(appicon);

            //load Register
            FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("view/CreProfile.fxml"));
            Parent registerParent = registerLoader.load();
            creProfileController = registerLoader.getController();
            creProfileController.setMainApp(this);
            creProfile = new Scene(registerParent);

            //load Login
            FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("view/Login.fxml"));
            Parent loginParen = loginLoader.load();
            loginController = loginLoader.getController();
            loginController.setMainApp(this);
            login = new Scene(loginParen);

            //load welcome
            FXMLLoader welcomeLoader = new FXMLLoader(getClass().getResource("view/Welcome.fxml"));
            Parent welcomeParent = welcomeLoader.load();
            welcomeController = welcomeLoader.getController();
            welcomeController.setMainApp(this);
            welcome = new Scene(welcomeParent);

            //load dashboard
            FXMLLoader dashboardLoader = new FXMLLoader(getClass().getResource("view/Dashboard.fxml"));
            Parent dashboardParent = dashboardLoader.load();
            dashboardController = dashboardLoader.getController();
            dashboardController.setMainApp(this);
            dashboard = new Scene(dashboardParent);

            //load stream
            FXMLLoader streamLoader = new FXMLLoader(getClass().getResource("view/Stream.fxml"));
            Parent streamParent = streamLoader.load();
            streamController = streamLoader.getController();
            streamController.setMainApp(this);
            stream = new Scene(streamParent);

            //load explorer
            FXMLLoader explorerLoader = new FXMLLoader(getClass().getResource("view/Explorer.fxml"));
            Parent explorerParent = explorerLoader.load();
            explorerController = explorerLoader.getController();
            explorerController.setMainApp(this);
            explorer = new Scene(explorerParent);

            this.primaryStage.setScene(login);
            this.primaryStage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void switchToWelcome() {
        dashboardController.trigger();
        streamController.trigger();
        double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
        primaryStage.setScene(welcome);
        if(stageWidth < 900) {
            primaryStage.setWidth(900);
            primaryStage.setHeight(600);
        }else{
            primaryStage.setWidth(stageWidth);
            primaryStage.setHeight(stageHeight);
        }

    }

    public void switchToDashboard() {
        double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
        primaryStage.setScene(dashboard);

    }

    public void switchToStream() {
        double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
        primaryStage.setScene(stream);
        primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
    }

    public void switchToLogin() {
        double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
        primaryStage.setScene(login);
        primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
    }

    public void switchToCreProfile() {
        double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
        primaryStage.setScene(creProfile);
        primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
    }

    public void switchToExplorer() {
        double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
        primaryStage.setScene(explorer);
        primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
    }

    public void switchToExplorer(String typeOfFilter, String typeOfTable) {
        double stageWidth = primaryStage.getWidth();
        double stageHeight = primaryStage.getHeight();
        explorerController.applyFilter(typeOfFilter, typeOfTable);
        primaryStage.setScene(explorer);
        primaryStage.setWidth(stageWidth);
        primaryStage.setHeight(stageHeight);
    }

    public static void main (String[] args) {
        launch(args);
    }
}
