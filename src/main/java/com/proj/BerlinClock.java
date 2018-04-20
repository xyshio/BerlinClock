package main.java.com.proj;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;




import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents Berlin Clock
 * <p>It allows to convert provided time into the format specific to Berlin Clock.</p>
 */
public class BerlinClock implements TimeConverter{

    private final static Logger logger = LoggerFactory.getLogger(BerlinClock.class);
    
    
    private char[] fivehours;
    private char[] onehours;
    private char[] fiveminutes;
    private char[] oneminutes;

    private char second;

    public BerlinClock() {
        resetClock();
    }

    /**
     * Returns time in Berlin Clock time format.
     * <p>Berlin Clock format is specified as follows:
     * <ul>
     * <li>First row indicates seconds. If current second is odd the value is O, when current second is even the value is Y.</li>
     * <li>Second row has 4 values indicating five hour intervals that has passed since midnight.
     * If interval passed the value is R otherwise it is O.</li>
     * <li>Third row has 4 values indicating one hour intervals that has passed since last five hour interval from second row.
     * If this interval passed the value is R otherwise it is O.</li>
     * <li>Forth row has 11 values indicating five minute intervals that has passed since last hour.
     * If interval passed the value is Y or R (for values #3, #6, #9) otherwise it is O.</li>
     * <li>Fifth row has 4 values indicating one minute intervals that has passed since last five minute interval from forth row.
     * If this interval passed the value is Y otherwise it is O.</li>
     * </ul>
     * </p>
     * <p>
     * Example:<br/>
     * Time 13:17:01 should be converted to folllowing value:<br/>
     * O<br/>
     * RROO<br/>
     * RRRO<br/>
     * YYROOOOOOOO<br/>
     * YYOO
     * </p>
     * @param aTime a time to be converted in following formats: hh:mm:ss or hh:mm
     * @return the time in Berlin Clock format, if provided time cannot be converted into proper Berlin Clock format, null value is returned
     */

    @Override
    public String convertTime(String aTime) {

        String result = null;
        LocalTime localTime;

        resetClock();

        //24:00:00 is a special value that is treated separately due to inability to convert it to valid LocalTime
        if ("24:00:00".equals(aTime)){
            result = "Y\n" +
                    "RRRR\n" +
                    "RRRR\n" +
                    "OOOOOOOOOOO\n" +
                    "OOOO";
            lox("Time "+aTime+" was successfully converted to Berlin Clock format: "+result.replaceAll("\\s+",""));
        }else{
            try{
                localTime = LocalTime.parse(aTime, DateTimeFormatter.ISO_LOCAL_TIME);
                setTime(localTime);
                result = getBerlinClockTime();
                lox("Time "+aTime+" was successfully converted to Berlin Clock format: "+result.replaceAll("\\s+",""));
            }catch (DateTimeParseException e){
                result = null;
                lox("Time \""+aTime+"\" was not converted to Berlin Clock format due to DateTimeParseException with following reason: " + e.getLocalizedMessage());
            }catch (RuntimeException e){
                result = null;
                lox("Value \""+aTime+"\" was not converted to Berlin Clock format due to "+e.toString());
            }
        }
        return result;
    }

    private void setTime(LocalTime time){
        second = (time.getSecond() % 2 == 0 ? 'Y' : 'O');

        for (int i = 0; i < time.getHour() / 5; i++) {
            fivehours[i] = 'R';
        }

        for (int i = 0; i < time.getHour() % 5; i++) {
            onehours[i] = 'R';
        }

        for (int i = 0; i < time.getMinute() / 5; i++) {
            fiveminutes[i] = (i + 1) % 3 == 0 ? 'R' : 'Y';
        }

        for (int i = 0; i < time.getMinute() % 5; i++) {
            oneminutes[i] = 'Y';
        }
//        System.out.println("");
    }

    private void resetClock(){
        fivehours = new char[]{'O', 'O', 'O', 'O'};
        onehours = new char[]{'O', 'O', 'O', 'O'};
        fiveminutes = new char[]{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'};
        oneminutes = new char[]{'O', 'O', 'O', 'O'};

        second = 'O';
    }

    private String getBerlinClockTime(){
        return String.valueOf(second)+"\n"
                +String.valueOf(fivehours)+"\n"
                +String.valueOf(onehours)+"\n"
                +String.valueOf(fiveminutes)+"\n"
                +String.valueOf(oneminutes);
    }
    
    public static void lox(String log){
    	System.out.println(log);
    }
    
    
}
