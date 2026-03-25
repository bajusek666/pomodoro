package ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class FileChooserComponent {
    private Desktop desktop = Desktop.getDesktop();
    private Button button;
    private File file;

    public FileChooserComponent(String choiceButtonLabel){
        this.button = new Button(choiceButtonLabel);
    }

    public HBox getUI(){
        HBox layout = new HBox(10);
        Label label = new Label("No file choosen.");

        layout.getChildren().addAll(this.button, label);

        button.setOnAction((event) -> {
            chooseFile();
        });

        return layout;
    }

    private void chooseFile(){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();

        this.file = fileChooser.showOpenDialog(stage);

        if(file != null){
            try {
                desktop.open(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        stage.close();
    }
}
