package hust.soict.cybersec.webloganalysis.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.scene.control.ComboBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Config {
    public static String apacheLogPath;
    public static String modsecLogPath;

    public static void createNewProfile(JsonObject profile) {
        apacheLogPath = profile.get("apacheLogPath").getAsString();
        modsecLogPath = profile.get("modsecLogPath").getAsString();

        String filePath = "src/main/resources/hust/soict/cybersec/webloganalysis/profile.txt";
        try (FileWriter writer = new FileWriter(filePath, true)){
            writer.write(profile.toString() + "\n");
        }
        catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }

    public static void loadProfile(ComboBox<String> profileCb) {
        String filePath = "src/main/resources/hust/soict/cybersec/webloganalysis/profile.txt";
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                JsonObject profile = gson.fromJson(line, JsonObject.class);
                profileCb.getItems().add(profile.get("profileName").getAsString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkProfile(String pass, String name) {
        String filePath = "src/main/resources/hust/soict/cybersec/webloganalysis/profile.txt";
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                JsonObject profile = gson.fromJson(line, JsonObject.class);
                if (profile.get("profileName").getAsString().equals(name) && profile.get("password").getAsString().equals(pass)) {
                    apacheLogPath = profile.get("apacheLogPath").getAsString();
                    modsecLogPath = profile.get("modsecLogPath").getAsString();
                    return true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
