package test.java.com.proj.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import main.java.com.proj.BerlinClock;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Set of positive tests for converting time into Berlin Clock format
 */
@RunWith(Parameterized.class)
public class BerlinClockPositiveTest {

    public String berlinTime;
    public String localTime;

    public BerlinClock bc = new BerlinClock();

    public BerlinClockPositiveTest(String localTime, String berlinTime) {
        this.localTime = localTime;
        this.berlinTime = berlinTime;
    }    
    
    @Parameterized.Parameters
    public static Collection berlinClockTimes() {
        return Arrays.asList(new String[][] {
                { "00:00:00", "YOOOOOOOOOOOOOOOOOOOOOOO" },
                { "00:00:01", "OOOOOOOOOOOOOOOOOOOOOOOO" },
                { "23:59:59", "ORRRRRRROYYRYYRYYRYYYYYY" },
                { "24:00:00", "Y\n" +
                        "RRRR\n" +
                        "RRRR\n" +
                        "OOOOOOOOOOO\n" +
                        "OOOO" },
                { "06:06:11", "O\n" +
                        "ROOO\n" +
                        "ROOO\n" +
                        "YOOOOOOOOOO\n" +
                        "YOOO" },
                { "12:12:22", "Y\n" +
                        "RROO\n" +
                        "RROO\n" +
                        "YYOOOOOOOOO\n" +
                        "YYOO" },
                { "19:18:33", "O\n" +
                        "RRRO\n" +
                        "RRRR\n" +
                        "YYROOOOOOOO\n" +
                        "YYYO" },
                { "22:34:44", "Y\n" +
                        "RRRR\n" +
                        "RROO\n" +
                        "YYRYYROOOOO\n" +
                        "YYYY" },
                { "20:45:55", "O\n" +
                        "RRRR\n" +
                        "OOOO\n" +
                        "YYRYYRYYROO\n" +
                        "OOOO" },
                { "00:39", "Y\n" +
                        "OOOO\n" +
                        "OOOO\n" +
                        "YYRYYRYOOOO\n" +
                        "YYYY" },
                { "23:59", "Y\n" +
                        "RRRR\n" +
                        "RRRO\n" +
                        "YYRYYRYYRYY\n" +
                        "YYYY" },
                { "00:00", "Y\n" +
                        "OOOO\n" +
                        "OOOO\n" +
                        "OOOOOOOOOOO\n" +
                        "OOOO" },
                { "00:01", "Y\n" +
                        "OOOO\n" +
                        "OOOO\n" +
                        "OOOOOOOOOOO\n" +
                        "YOOO" }
        });
    }


    @Test
    public void testConvertTime() throws Exception {
        assertThat(this.bc.convertTime(localTime)).as("Valid time should returned if following value \"%s\" is passed as parameter.", localTime).isEqualTo(berlinTime);
    }
}