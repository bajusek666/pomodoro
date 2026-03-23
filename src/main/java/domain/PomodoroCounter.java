package domain;

import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableNumberValue;

import java.time.Instant;

public class PomodoroCounter implements CounterController{
    private int focusDuration;
    private int restDuration;
    private boolean state;
    private double currentTime;
    private CounterState counterState;
    private Instant instant;

    public PomodoroCounter(){
        this.restDuration = 5;
        this.focusDuration = 30;
        this.state = false;
        this.counterState = CounterState.FOCUS;
        this.currentTime = 0;
    }

    public PomodoroCounter(int focusTime, int restTime){
        this();
        this.focusDuration = focusTime;
        this.restDuration = restTime;
    }

    public void start(){
        this.state = true;

        instant = Instant.now();
        long startTime = instant.toEpochMilli();

        while(this.state){
            counterState = CounterState.FOCUS;

            instant = Instant.now();
            currentTime = instant.toEpochMilli() - startTime;

            if(currentTime >= focusDuration){
                rest();
                startTime = Instant.now().toEpochMilli();
            }
        }
    }

    public void rest(){
        counterState = CounterState.REST;

        instant = Instant.now();
        long startTime = instant.toEpochMilli();

        while(this.state){
            currentTime = instant.toEpochMilli() - startTime;

            if(currentTime >= restDuration){
                break;
            }
        }
    }

    public void stop(){
//        this.state = false;
    }

    public void reset(){
        this.state = false;
        currentTime = 0;
    }

    public void setFocusDuration(int duration){
        this.focusDuration = duration;
    }

    public void setRestDuration(int duration){
        this.restDuration = duration;
    }

}
