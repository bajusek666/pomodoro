package domain;

public interface CounterController {
    void start();
    void stop();
    void reset();
    int readTime();
    void setFocusDuration(int duration);
    void setRestDuration(int duration);
}
