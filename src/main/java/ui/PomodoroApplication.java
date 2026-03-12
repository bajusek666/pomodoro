package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class PomodoroApplication extends Application {
    public void start(Stage stage){
        Label title = new Label("Pomodoro Timer");
        Button start = new Button("Start");
        Button stop = new Button("Stop");
        Button reset = new Button("Reset");

        FlowPane componentGroup = new FlowPane();
        componentGroup.getChildren().add(title);
        componentGroup.getChildren().add(start);
        componentGroup.getChildren().add(stop);
        componentGroup.getChildren().add(reset);

        Scene scene = new Scene(componentGroup);

        stage.setScene(scene);
        stage.show();
    }

    public static void launch(){
        launch(PomodoroApplication.class);
    }
}
