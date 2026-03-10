import domain.CounterState;
import domain.PomodoroCounter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PomodoroControllerTest {
    private PomodoroCounter pomodoroCounter;

    public PomodoroControllerTest(){
        this.pomodoroCounter = new PomodoroCounter();
    }


    @Test
    public void remainingTimeEqualsFocusTime(){
        Assert.assertEquals(pomodoroCounter.readTime(), 30);
    }

    @Test
    public void counterStatusIsFocus(){
        Assert.assertEquals(pomodoroCounter.readState(), CounterState.FOCUS);
    }

}
