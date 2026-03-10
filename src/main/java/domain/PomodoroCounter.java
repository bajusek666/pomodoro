package domain;

public class PomodoroCounter implements CounterController{
    private int focusDuration;
    private int restDuration;
    private int timeRemaining;
    private boolean state;
    private CounterState counterState;

    public PomodoroCounter(){
        this.restDuration = 5;
        this.focusDuration = 30;
        this.timeRemaining = this.focusDuration;
        this.state = false;
        this.counterState = CounterState.FOCUS;
    }

    public void start(){
        this.state = true;

        while(state){
            counterState = CounterState.FOCUS;
            timeRemaining--;
            //wait one second
            if(timeRemaining == 0){
                rest();
                timeRemaining = focusDuration;
            }
        }
    }

    public void rest(){
        counterState = CounterState.REST;
        int timeRemaining = this.restDuration;
        while(timeRemaining > 0){
            timeRemaining--;
            //wait 1 second
        }
    }

    public void stop(){

    }

    public void reset(){
        this.state = false;
        this.timeRemaining = focusDuration;
    }

    public int readTime(){
        return this.timeRemaining;
    }

    public void setFocusDuration(int duration){
        this.focusDuration = duration;
    }

    public void setRestDuration(int duration){
        this.restDuration = duration;
    }
}
