package domain;

public interface CounterController {
    void start() throws InterruptedException;
    void stop();
    void reset() throws InterruptedException;
    void setFocusDuration(int duration);
    void setRestDuration(int duration);
}
