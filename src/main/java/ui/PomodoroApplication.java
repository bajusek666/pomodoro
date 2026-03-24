package ui;

import domain.PomodoroCounter;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PomodoroApplication extends Application {
    private PomodoroCounter pomodoroCounter;
    private BorderPane layout;
    private BorderPane timerLayout;
    private FlowPane configLayout;
    private Insets defaultInsets;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private Future<?> future;

    public static void launch() {
        launch(PomodoroApplication.class);
    }

    public void start(Stage stage) {
        this.pomodoroCounter = new PomodoroCounter(25, 5);
        this.layout = new BorderPane();
        this.defaultInsets = new Insets(10, 10, 10, 10);
        this.layout.setPrefSize(300, 200);

        HBox menu = createMenu();
        this.timerLayout = createTimerLayout();
        this.configLayout = createConfigLayout();

        this.layout.setTop(menu);
        this.layout.setCenter(this.timerLayout);

        Scene scene = new Scene(this.layout);
        stage.setScene(scene);
        stage.show();
    }

    private HBox createMenu() {
        HBox menu = new HBox(20);

        Button timer = new Button("Timer");
        Button config = new Button("Configuration");
        menu.getChildren().addAll(timer, config);

        menu.setPadding(defaultInsets);
        menu.setAlignment(Pos.CENTER);

        timer.setOnAction((event) -> this.layout.setCenter(this.timerLayout));

        config.setOnAction((event) -> this.layout.setCenter(this.configLayout));

        return menu;
    }

    private BorderPane createTimerLayout() {
        BorderPane timerLayout = new BorderPane();

        Label timerLabel = new Label("00:00");
        timerLabel.setFont(Font.font(30));

        new AnimationTimer() {
            @Override
            public void handle(long l) {
                timerLabel.setText(buildTimeString());
            }
        }.start();

        Button stop = new Button("Stop");
        Button start = new Button("Start");
        Button reset = new Button("Reset");

        HBox timerControls = new HBox(stop, start, reset);
        timerControls.setAlignment(Pos.CENTER);
        timerControls.setSpacing(20);
        timerControls.setPadding(defaultInsets);

        timerLayout.setCenter(timerLabel);
        timerLayout.setBottom(timerControls);

        stop.setOnAction((event) -> this.pomodoroCounter.stop());

        start.setOnAction((event) -> startCounter());

        reset.setOnAction((event) -> this.pomodoroCounter.reset());

        return timerLayout;
    }

    private String buildTimeString(){
        int seconds = pomodoroCounter.getCurrentSeconds();
        int minutes = (int)Math.floor(seconds/60);
        seconds = seconds % 60;

        StringBuffer timeString = new StringBuffer();
        timeString.append(minutes);
        timeString.append(":");
        if(seconds < 10){
            timeString.append(0);
        }
        timeString.append(seconds);
        return timeString.toString();
    }

    private void startCounter(){
        if(future == null || future.isDone()){
            future = executor.submit(pomodoroCounter);
        }else{
            System.out.println("Counter is already running!");
        }
    }

    private FlowPane createConfigLayout() {
        FlowPane configLayout = new FlowPane();
        configLayout.setAlignment(Pos.CENTER);

        HBox focusBox = new HBox(10);
        HBox restBox = new HBox(10);

        Label focusDurationLabel = new Label("Focus duration [min]:");
        Slider focusDurationSlider = new Slider(10, 115, 50);
        Label focusDurationValue = new Label(String.valueOf((int) Math.round(focusDurationSlider.getValue())));

        Label restDurationLabel = new Label("Rest duration [min]:");
        Slider restDurationSlider = new Slider(5, 60, 10);
        Label restDurationValue = new Label(String.valueOf((int) Math.round(restDurationSlider.getValue())));

        focusBox.getChildren().addAll(focusDurationLabel, focusDurationSlider, focusDurationValue);
        restBox.getChildren().addAll(restDurationLabel, restDurationSlider, restDurationValue);

        focusDurationSlider.setShowTickLabels(true);
        focusDurationSlider.setShowTickMarks(true);

        restDurationSlider.setShowTickLabels(true);
        restDurationSlider.setShowTickMarks(true);

        focusDurationSlider.valueProperty().addListener((observableValue, oldVal, newVal) -> {
            this.pomodoroCounter.reset();
            this.pomodoroCounter.setFocusDuration(newVal.intValue());
            focusDurationValue.setText(String.valueOf(newVal.intValue()));
        });

        restDurationSlider.valueProperty().addListener((observableValue, oldVal, newVal) -> {
            this.pomodoroCounter.reset();
            this.pomodoroCounter.setRestDuration(newVal.intValue());
            restDurationValue.setText(String.valueOf(newVal.intValue()));
        });

        configLayout.getChildren().addAll(focusBox, restBox);

        return configLayout;
    }

}
