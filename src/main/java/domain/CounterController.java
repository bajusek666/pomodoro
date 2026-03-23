package domain;

public interface CounterController {
    void start();
    void stop();
    void reset();
    void setFocusDuration(int duration);
    void setRestDuration(int duration);
}
