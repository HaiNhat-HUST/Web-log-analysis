package hust.soict.cybersec.webloganalysis.util;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import hust.soict.cybersec.webloganalysis.model.Parser.ParseAccessLog;

public class LogLineChart  {

    public static void addData(LineChart<String, Number> lineChart, String dateTarget){
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        String filePath = Config.apacheLogPath;
        try {
            ParseAccessLog parser = new ParseAccessLog();
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (parser.getDate(line).equals(dateTarget)) {
                    int hour = parser.getHour(line);
                    if (hm.containsKey(hour)){
                        hm.put(hour, hm.get(hour) + 1);
                    }
                    else{
                        hm.put(hour, 1);
                    }
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Series<String, Number> series = new Series<>();
        series.setName("Log per hour");
        for(int hour : hm.keySet()){
            series.getData().add(new Data<String, Number>(Integer.toString(hour), hm.get(hour)));
        }
        lineChart.getData().add(series);
    }
}
