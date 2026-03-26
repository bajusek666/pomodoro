# Pomodoro Timer
## Desktop application that helps you focus using pomodoro technique. 
### Java, JavaFx

Features
- Configure your own focus and rest duration.
- Simple, not distractive UI.
- Runs focus and rest cycles continuously until stopped or reset. 
- Set your own sound signals for the end of rest and focus cycle, so you don't have to look at the timer.

![Timer tab](pomodoro_app_timer.png)
![Coniguration tab](pomodoro_app_config.png)

### Architecture solutions.

PomodoroTimer class is decoupled from the rest of the application, runnable class, so it can be used in any other project. 
UI Components are in separate classes, and send state,file, ...,  changes to the application via ChangeListeners. 
