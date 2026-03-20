package ui;

import domain.PomodoroCounter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PomodoroApplication extends Application {
    private PomodoroCounter pomodoroCounter;
    private BorderPane layout;
    private BorderPane timerLayout;
    private FlowPane configLayout;

    public void start(Stage stage){
        this.pomodoroCounter = new PomodoroCounter();
        this.layout = new BorderPane();

        HBox menu = createMenu();
        this.timerLayout = createTimerLayout();
        this.configLayout = createConfigLayout();

        this.layout.setTop(menu);
        this.layout.setCenter(this.timerLayout);

        Scene scene = new Scene(this.layout);
        stage.setScene(scene);
        stage.show();
    }

    private HBox createMenu(){
        HBox menu = new HBox();

        Button timer = new Button("Timer");
        Button config = new Button("Configuration");
        menu.getChildren().addAll(timer, config);

        timer.setOnAction((event) -> {
            this.layout.setCenter(this.timerLayout);
        });

        config.setOnAction((event) -> {
            this.layout.setCenter(this.configLayout);
        });

        return menu;
    }

    private BorderPane createTimerLayout() {
        BorderPane timerLayout = new BorderPane();

        Label timer = new Label("00:00");
        timer.setFont(Font.font(20));

        Button stop = new Button("Stop");
        Button start = new Button("Start");
        Button reset = new Button("Reset");

        HBox timerControls = new HBox(stop, start, reset);

        timerLayout.setCenter(timer);
        timerLayout.setBottom(timerControls);

        stop.setOnAction((event) -> {
            this.pomodoroCounter.stop();
        });

        start.setOnAction((event) -> {
            this.pomodoroCounter.start();
        });

        reset.setOnAction((event) -> {
            this.pomodoroCounter.reset();
        });

        return timerLayout;
    }

    private FlowPane createConfigLayout(){
        FlowPane configLayout = new FlowPane();

        Label focusDurationLabel = new Label("Focus duration in minutes:");
        Slider focusDurationSlider = new Slider(10, 115, 25);
        Label restDurationLabel = new Label("Rest duration in minutes:");
        Slider restDurationSlider = new Slider(5, 60, 5);

        configLayout.getChildren().addAll(focusDurationLabel, focusDurationSlider, restDurationLabel, restDurationSlider);

        return configLayout;
    }

    public static void launch(){
        launch(PomodoroApplication.class);
    }
}
