package ui;

import domain.FileChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileChooserComponent {
    private final Button button;
    private File file;
    private Label label;
    private HBox layout;
    private FileChangeListener listener;

    public FileChooserComponent(String choiceButtonLabel) {
        button = new Button(choiceButtonLabel);
    }

    public HBox getComponent() {
        layout = new HBox(10);
        label = new Label("No file chosen.");
        layout.getChildren().addAll(button, label);

        styleElements();

        button.setOnAction((event) -> chooseFile());

        return layout;
    }

    private void styleElements() {
        button.setPadding(new Insets(5, 5, 5, 5));
        label.setPadding(new Insets(5, 5, 5, 5));
        label.setFont(Font.font(12));
        layout.setAlignment(Pos.CENTER);
        label.setWrapText(true);
    }

    private void chooseFile() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();

        File submitedFile = fileChooser.showOpenDialog(stage);
        if (isFileValid(submitedFile)) {
            file = submitedFile;
            notifyListener();
            setLabelToPath();
        } else {
            label.setText("File must be .mp3 format");
            file = null;
        }

        stage.close();
    }

    private boolean isFileValid(File file) {
        if (file == null) {
            return false;
        }
        return file.getName().toLowerCase().endsWith(".mp3");
    }

    private void notifyListener() {
        if (listener != null) {
            this.listener.onChange(file);
        }
    }

    private void setLabelToPath() {
        if (file != null) {
            label.setText(file.getName());
        }
    }

    public void setListener(FileChangeListener listener) {
        this.listener = listener;
    }
}
