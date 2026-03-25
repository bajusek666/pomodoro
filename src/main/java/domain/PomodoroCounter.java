package domain;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class PomodoroCounter implements CounterController, Runnable{
    private int focusDuration;
    private int restDuration;
    private boolean running;
    private long currentTime;
    private CounterState counterState;
    private Instant instant;
    private static final int MILLISECONDS_IN_MINUTE = 60000;

    public PomodoroCounter(){
        setRestDuration(5);
        setFocusDuration(25);
        this.running = false;
        this.counterState = CounterState.FOCUS;
        this.currentTime = 0;
    }

    public PomodoroCounter(int focusTime, int restTime){
        this();
        setRestDuration(restTime);
        setFocusDuration(focusTime);
    }

    public void run(){
        try {
            start();
        }catch(InterruptedException e){
            System.out.println("Program przerwany");
            Thread.currentThread().interrupt();
        }
    }

    public void start() throws InterruptedException{
        this.running = true;

        instant = Instant.now();
        long startTime = instant.toEpochMilli();
        System.out.println("Focus started");

        while(this.running){

            if(Thread.currentThread().isInterrupted()){
                throw new InterruptedException();
            }

            counterState = CounterState.FOCUS;

            instant = Instant.now();
            currentTime = instant.toEpochMilli() - startTime;

            if(currentTime >= focusDuration){
                System.out.println("Focus ended");
                rest();
                startTime = Instant.now().toEpochMilli();
            }
        }
    }

    public void rest() throws InterruptedException{
        counterState = CounterState.REST;
        System.out.println("Rest started");

        instant = Instant.now();
        long startTime = instant.toEpochMilli();

        while(this.running){

            if(Thread.currentThread().isInterrupted()){
                throw new InterruptedException();
            }

            instant = Instant.now();
            currentTime = instant.toEpochMilli() - startTime;

            if(currentTime >= restDuration){
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
        this.focusDuration = duration * MILLISECONDS_IN_MINUTE;
    }

    public void setRestDuration(int duration){
        this.restDuration = duration * MILLISECONDS_IN_MINUTE;
    }

    public CounterState getCounterState(){
        return this.counterState;
    }

    public boolean isRunning(){
        return this.running;
    }

    public int getCurrentSeconds(){
        return (int)TimeUnit.MILLISECONDS.toSeconds(this.currentTime);
    }
}
