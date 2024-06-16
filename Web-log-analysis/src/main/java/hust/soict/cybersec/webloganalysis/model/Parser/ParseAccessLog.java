package hust.soict.cybersec.webloganalysis.model.Parser;

import hust.soict.cybersec.webloganalysis.model.LogEntry.AccessLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseAccessLog {
    private final String timeRegex = "\\[(.*?)\\]";
    private final String ipRegex = "^([\\d.]+)";
    private final String uaRegex = "^\\S+ \\S+ \\S+ \\[.+?\\] \\\".+?\\\" \\S+ \\S+ \\\".+?\\\" \\\"(.+?)\\\"";
    private final String requestRegex = "\"([^\"]+)\"";
    private final String statusRegex = "(\\d+)\s(\\d+)";
    private final String bytesRegex = "(\\d+|-)";
    private final String refererRegex = "^.*\\\"(?:GET|POST) .*\\\" \\d{3} \\d+ \\\"([^\\\"]*)\\\"";

    public ParseAccessLog(){

    }

    public AccessLog parse(String logEntry){
        return new AccessLog(
                getIp(logEntry),
                "-",
                "-",
                getTime(logEntry),
                getUserAgent(logEntry),
                getRequest(logEntry),
                getStatusCode(logEntry),
                getBytesSent(logEntry),
                getReferer(logEntry)
        );
    }

    public String getIp(String logEntry){
        Pattern pattern = Pattern.compile(ipRegex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            return matcher.group(1);
        }
        else{
            return "-";
        }
    }

    public String getTime(String logEntry){
        Pattern pattern = Pattern.compile(timeRegex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            return matcher.group(1);
        }
        else{
            return "-";
        }
    }

    @SuppressWarnings("deprecation")
    public int getHour(String logEntry){
        int hour = 0;
        SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
        Date dateTime;
        try {
            dateTime = format.parse(getTime(logEntry));
            hour = dateTime.getHours();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hour;
    }

    public String getUserAgent(String logEntry){
        Pattern pattern = Pattern.compile(uaRegex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            return matcher.group(1);
        }
        else{
            return "-";
        }
    }

    public String getRequest(String logEntry){
        Pattern pattern = Pattern.compile(requestRegex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            return matcher.group(1);
        }
        else{
            return "-";
        }
    }

    public int getStatusCode(String logEntry){
        Pattern pattern = Pattern.compile(statusRegex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            return Integer.parseInt(matcher.group(1));
        }
        else{
            return 0;
        }
    }

    public int getBytesSent(String logEntry){
        Pattern pattern = Pattern.compile(bytesRegex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            return Integer.parseInt(matcher.group(1));
        }
        else{
            return 0;
        }
    }

    public String getReferer(String logEntry){
        Pattern pattern = Pattern.compile(refererRegex);
        Matcher matcher = pattern.matcher(logEntry);
        if (matcher.find()){
            return matcher.group(1);
        }
        else{
            return "-";
        }
    }

    @SuppressWarnings("finally")
    public String getDate(String logEntry){
        String inputDate = getTime(logEntry);
        String newDateFormat = "yyyy-MM-dd";

        SimpleDateFormat oldFormat = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
        SimpleDateFormat newFormat = new SimpleDateFormat(newDateFormat);

        String outputDate = "";
        try {
            Date oldDate = (Date) oldFormat.parse(inputDate);
            String newDateString = newFormat.format(oldDate);
            outputDate = newDateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        finally{
            return outputDate;
        }
    }
}
