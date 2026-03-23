package domain;

import java.time.Instant;

public class PomodoroCounter implements CounterController, Runnable{
    private int focusDuration;
    private int restDuration;
    private boolean running;
    private double currentTime;
    private CounterState counterState;
    private Instant instant;

    public PomodoroCounter(){
        this.restDuration = 5;
        this.focusDuration = 30;
        this.running = false;
        this.counterState = CounterState.FOCUS;
        this.currentTime = 0;
    }

    public PomodoroCounter(int focusTime, int restTime){
        this();
        this.focusDuration = focusTime;
        this.restDuration = restTime;
    }

    public void run(){
        start();
    }

    public void start(){
        this.running = true;

        instant = Instant.now();
        long startTime = instant.toEpochMilli();
        System.out.println("Focus started");

        while(this.running){

            counterState = CounterState.FOCUS;

            instant = Instant.now();
            currentTime = instant.toEpochMilli() - startTime;

            if(currentTime >= focusDuration*1000){
                System.out.println("Focus ended");
                rest();
                startTime = Instant.now().toEpochMilli();
            }
        }
    }

    public void rest(){
        counterState = CounterState.REST;
        System.out.println("Rest started");

        instant = Instant.now();
        long startTime = instant.toEpochMilli();

        while(this.running){

            instant = Instant.now();
            currentTime = instant.toEpochMilli() - startTime;

            if(currentTime >= restDuration*1000){
                System.out.println("Rest ended");
                return;
            }
        }
    }

    public void stop(){
        this.running = false;
    }

    public void reset(){
        this.running = false;
        currentTime = 0;
    }

    public void setFocusDuration(int duration){
        this.focusDuration = duration;
    }

    public void setRestDuration(int duration){
        this.restDuration = duration;
    }

    public CounterState getCounterState(){
        return this.counterState;
    }

    public boolean isRunning(){
        return this.running;
    }

    public double getCurrentTime(){
        return this.currentTime;
    }

}
