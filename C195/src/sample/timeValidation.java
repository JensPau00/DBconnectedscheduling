package sample;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class timeValidation {
        static AlertError lambdaAlert = (header, content, error) -> {
        AlertError.alert.setHeaderText(header);
        AlertError.alert.setContentText(content);
        AlertError.alert.setTitle(error);
        AlertError.alert.showAndWait();};
        static ZonedDateTime instant = ZonedDateTime.now();
        static ZoneId timeZone = ZoneId.of("America/New_York");
        static ZoneId localTime = ZoneId.systemDefault();


    public static boolean checkTime(String time){
        boolean isTime=true;
        String timeZL;
        String timeZET;
        timeZL=String.valueOf(localTime.getRules()).split("=")[1];
        int timeZh = Integer.parseInt(timeZL.split(":")[0]);
        int timeZm = Integer.parseInt(timeZL.split(":")[1].split("]")[0]);
        String timeET = String.valueOf(timeZone.getRules()).split("=")[1];
        int timeETh = Integer.parseInt(timeET.split(":")[0]);
        int hour = ((Integer.parseInt(time.split(":")[0]))+(timeETh-timeZh))%24;
        int min = Integer.parseInt(time.split(":")[1]);

        try{
            System.out.println(hour);
            if (timeZm!= 0 && min>=0 && min<60){
                if (timeZh<0){
                    timeZm = -1*timeZm;
                }
                min = min+timeZm;
                min = min%60;
            }
            if (hour>22 || hour<8){
                isTime=false;
                lambdaAlert.displayAlert("Hours incorrect","","Hours need to be between 8 and 22 eastern time");
            }
            if (min>60 ||min<0){
                isTime=false;
                lambdaAlert.displayAlert("Minutes above or below 60","","correct your minutes");
            }
        }
        catch (Exception e ){
            isTime = false;
            lambdaAlert.displayAlert("your time isn't formatted correctly","","FORMAT time like HH:mm using 24 hour time");

        }
        return isTime;
    }
    public static List<Integer> getTime(String time){
        List<Integer> timeList = new ArrayList<Integer>();
        if (checkTime(time)){
            int hour = Integer.parseInt(time.split(":")[0]);
            int min = Integer.parseInt(time.split(":")[1]);
            timeList.add(hour);
            timeList.add(min);
        }
        return timeList;
    }
    public static boolean startSooner(List<Integer> start,List<Integer> end){
        boolean isSooner=true;
        if (start.get(0)>end.get(0)){
            if (start.get(1)>end.get(1)){
                isSooner = false;
                lambdaAlert.displayAlert("Incorrect times","","The appointment should end after it starts");
            }
        }
        return isSooner;
    }
}
