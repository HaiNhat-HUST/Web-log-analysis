package hust.soict.cybersec.webloganalysis.model.Parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import hust.soict.cybersec.webloganalysis.model.LogEntry.AuditLog;

public class ParseAuditLog {
    private final String ruleRegex = "(?i)attack\\-(?:\\w+\\-)*([a-z]+)\\.conf";
    private Pattern pattern;
    private Gson gson;

    public ParseAuditLog() {
        pattern = Pattern.compile(ruleRegex);
        gson = new Gson();
    }

    public AuditLog parse(String log) {
        return new AuditLog(getTimestamp(log), getRemoteAddress(log), getRequest(log), getTriggeredRule(log));
    }

    private JsonObject getJsonObject(String log) {
        return gson.fromJson(log, JsonObject.class);
    }

    public String getTimestamp(String log) {
        JsonObject transaction = getJsonObject(log).get("transaction").getAsJsonObject();
        return transaction.get("time").getAsString();
    }

    public String getRemoteAddress(String log) {
        JsonObject transaction = getJsonObject(log).get("transaction").getAsJsonObject();
        return transaction.get("remote_address").getAsString();
    }

    public String getRequest(String log) {
        return getJsonObject(log).get("request").getAsJsonObject().get("request_line").getAsString();
    }

    public String getTriggeredRule(String log) {
        JsonObject auditData = getJsonObject(log).get("audit_data").getAsJsonObject();
        JsonArray messages = auditData.getAsJsonArray("messages");
        if (messages == null) {
            return null;
        }
        for (int i = 0; i < messages.size(); i++) {
            String message = messages.get(i).getAsString();
            Matcher matcher = pattern.matcher(message);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    @SuppressWarnings("finally")
    public String getDate(String log) {
        String inputDate = getTimestamp(log);
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
