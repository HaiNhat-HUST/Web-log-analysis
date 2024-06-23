package hust.soict.cybersec.webloganalysis.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hust.soict.cybersec.webloganalysis.Main;
import javafx.scene.control.ComboBox;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config {
    public static String apacheLogPath;
    public static String modsecLogPath;
    public static String currentPath;

    public static void createNewProfile(JsonObject profile) {
        apacheLogPath = profile.get("apacheLogPath").getAsString();
        modsecLogPath = profile.get("modsecLogPath").getAsString();
        
        try (FileWriter writer = new FileWriter(currentPath + File.separator + "profile.txt", true)){
            writer.write(profile.toString() + "\n");
        }
        catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

    }

    public static void loadProfile(ComboBox<String> profileCb) {
        String filePath = currentPath + File.separator + "profile.txt";
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
        String filePath = currentPath + File.separator + "profile.txt";
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

    public static void logInAsVisitor() {
        String accessFile = currrentPath + File.separator + "access.log";
        String modsecFile = currrentPath + File.separator + "modsec.log";
        apacheLogPath = accessFile;
        modsecLogPath = modsecFile;
        Path accessPath = Paths.get(accessFile);
        Path modsecPath = Paths.get(modsecFile);
        if (!Files.exists(accessPath) || !Files.exists(modsecPath)) {
            try {
                Path newAccessFile = Files.createFile(accessPath);
                Path newModsecFile = Files.createFile(modsecPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            return;
        }

        InputStream inputStream = Main.class.getResourceAsStream("log_sample/access.log");
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(accessFile)));
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        InputStream inputStream2 = Main.class.getResourceAsStream("log_sample/modsec.log");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream2));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(modsecFile)));
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
