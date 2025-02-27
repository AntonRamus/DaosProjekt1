package src.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.application.models.Education;
import src.application.models.Exam;
import src.application.models.Student;
import src.storage.Storage;

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Karakterregistreringssystem");
        VBox vBox = new VBox(10);
        initContent(vBox);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(VBox vBox) {
        vBox.setPadding(new Insets(20));

        HBox topHbox = new HBox(10);
        HBox middleHBox = new HBox(10);
        HBox buttomHBox = new HBox(10);

        ComboBox<Education> uddannelseComboBox = new ComboBox<>();
        ComboBox<Exam> eksamenComboBox = new ComboBox<>();
        ListView<Student> studentListView = new ListView<>();
        Button button3 = new Button("Afvikel Eksamen");
        Button button4 = new Button("Opret Eksamens Forsøg");


        updateUddannelseComboBox(uddannelseComboBox);
        uddannelseComboBox.setOnAction(e -> updateEksamenComboBox(eksamenComboBox, uddannelseComboBox));

        studentListView.getItems().setAll();

        button3.setOnAction(event -> afvikelEksamen(uddannelseComboBox.getSelectionModel().getSelectedItem()));
        button4.setOnAction(e -> opretEksamensForsøgAction(null));

        topHbox.getChildren().addAll(uddannelseComboBox, eksamenComboBox);
        middleHBox.getChildren().add(studentListView);
        buttomHBox.getChildren().addAll(button3, button4);

        topHbox.setAlignment(Pos.CENTER);
        middleHBox.setAlignment(Pos.CENTER);
        buttomHBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(topHbox, middleHBox, buttomHBox);
    }

    private void afvikelEksamen(Education education) {
        if (education != null) {
            new AfvikelEksamenWindow(education).showAndWait();
        }
    }

    private void opretEksamensForsøgAction(Student student) {
        if (student != null) {
            new OpretEksamensForsøgWindow(student).showAndWait();
        }
    }

    private void updateUddannelseComboBox(ComboBox<Education> comboBox) {
        comboBox.getItems().setAll(Storage.getEducations());
    }

    private void updateEksamenComboBox(ComboBox<Exam> comboBox1, ComboBox<Education> comboBox2) {
        comboBox1.getItems().setAll(Storage.getExamsOnEducation(comboBox2.getSelectionModel().getSelectedItem()));
    }
}
