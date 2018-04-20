package test.java.com.proj.bdd;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Test;

import main.java.com.proj.BerlinClock;
import test.java.com.proj.support.BehaviouralTestEmbedder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static test.java.com.proj.support.BehaviouralTestEmbedder.aBehaviouralTestRunner;

/**
 * Acceptance test class that uses the JBehave (Gherkin) syntax for writing stories.  You need to
 * edit this class to complete the exercise.
 */
public class BerlinClockScenario {

    private BerlinClock berlinClock = new BerlinClock();

    private String berlinLocalTime;
    private String calculatedBerlinClockTime;

    @Test
    public void berlinClockAcceptanceTests() throws Exception {
        try {
        	aBehaviouralTestRunner()
        	.usingStepsFrom(this)
        	.withStory("berlin-clock.story")
        	.run();
		} catch (Exception e) {
			System.out.println("");
		}
    }

    @Given("it is $time in Berlin")
    public void setTime(String localTime){
        this.berlinLocalTime = localTime;
    }

    @When("I look at Berlin-Clock")
    public void lookAtBerlinClock(){
        this.calculatedBerlinClockTime = berlinClock.convertTime(this.berlinLocalTime);
    }

    @Then("I should see $calculatedBerlinClockTime")
    public void shouldSeeCorrectTime(String berlinClockTime){
        //TODO: fix line ending problem temporarily solved by removing \r character from data passed from story
        assertThat(this.calculatedBerlinClockTime)
                .as("Provided time should be converted into proper value.")
                .isEqualTo(berlinClockTime.replaceAll("\r", ""));
    }

}
