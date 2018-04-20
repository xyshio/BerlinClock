package test.java.com.proj.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import main.java.com.proj.BerlinClock;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * Set of negative tests for converting time into Berlin Clock format
 */
@RunWith(Parameterized.class)
public class BerlinClockNegativeTest {
    public BerlinClock berlinClock = new BerlinClock();

    public String wrongTime;

    @Parameterized.Parameters
    public static Collection berlinClockTimes() {
        return Arrays.asList(//special values
                null,
                "",
                //wrong time format
                "123401",
                "1",
                "12.23.01",
                //wrong time value
                "24:00:01",
                "00:60:00",
                "00:00:60",
                "00:00:60",
                //wrong text value
                "-1",
                "Litwo ojczyzno moja",
                "\u00A5");
    }

    public BerlinClockNegativeTest(String wrongTime) {
        this.wrongTime = wrongTime;
    }

    @Test
    public void shouldFailOnWrongValue(){
        assertThat(berlinClock.convertTime(this.wrongTime)).as("null value should be returned if \"%s\" value is passed as time to convert.", this.wrongTime).isNull();
    }

}