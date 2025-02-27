package src.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.application.models.Education;
import src.application.models.Exam;
import src.application.models.Examination;
import src.application.models.Student;
import src.storage.Storage;

//Gruppe 2 - Anton, Sidse og Victor

public class MainWindow extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Karakterregistreringssystem");
        VBox vBox = new VBox(10);
        initContent(vBox);
        Scene scene = new Scene(vBox, 500, 500);
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
        ComboBox<Examination> examinationComboBox = new ComboBox<>();
        ListView<Student> studentListView = new ListView<>();
        Button button1 = new Button("Afvikel Eksamen");
        Button button2 = new Button("Opret Eksamens Forsøg");
        Button button3 = new Button("Find elever på afvikling");


        updateUddannelseComboBox(uddannelseComboBox);
        uddannelseComboBox.setOnAction(event -> {
            updateEksamenComboBox(eksamenComboBox, uddannelseComboBox);
            updateListView(uddannelseComboBox, studentListView);
        });
        eksamenComboBox.setOnAction(event -> {
            updateExaminationComboBox(eksamenComboBox, examinationComboBox);
        });

        button1.setOnAction(event -> afvikelEksamen(uddannelseComboBox.getSelectionModel().getSelectedItem()));
        button2.setOnAction(event -> opretEksamensForsøgAction());
        button3.setOnAction(event -> hvisStuderendePaaTermin(eksamenComboBox.getSelectionModel().getSelectedItem(), examinationComboBox.getSelectionModel().getSelectedItem()));

        topHbox.getChildren().addAll(uddannelseComboBox, eksamenComboBox, examinationComboBox);
        middleHBox.getChildren().add(studentListView);
        buttomHBox.getChildren().addAll(button1, button2, button3);

        topHbox.setAlignment(Pos.CENTER);
        middleHBox.setAlignment(Pos.CENTER);
        buttomHBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(topHbox, middleHBox, buttomHBox);
    }

    private void hvisStuderendePaaTermin(Exam exam, Examination examination) {
        if (exam != null && examination != null) {
            StudentGradeList sgl = new StudentGradeList(exam, examination);
            sgl.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Select exam and examination");
            alert.setTitle("Error");
            alert.show();
        }
    }

    private void updateExaminationComboBox(ComboBox<Exam> eksamenComboBox, ComboBox<Examination> examinationComboBox) {
        examinationComboBox.getItems().setAll(Storage.getExaminationsOnExam(eksamenComboBox.getSelectionModel().getSelectedItem()));
    }

    private void updateListView(ComboBox<Education> uddannelseComboBox, ListView<Student> studentListView) {
        studentListView.getItems().setAll(Storage.getStudentsOnEducation(uddannelseComboBox.getSelectionModel().getSelectedItem()));
    }

    private void afvikelEksamen(Education education) {
        if (education != null) {
            new AfvikelEksamenWindow(education).showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Select education");
            alert.setTitle("Error");
            alert.show();
        }
    }

    private void opretEksamensForsøgAction() {

        new OpretEksamensForsoegWindow().showAndWait();

    }

    private void updateUddannelseComboBox(ComboBox<Education> comboBox) {
        comboBox.getItems().setAll(Storage.getEducations());
    }

    private void updateEksamenComboBox(ComboBox<Exam> comboBox1, ComboBox<Education> comboBox2) {
        comboBox1.getItems().setAll(Storage.getExamsOnEducation(comboBox2.getSelectionModel().getSelectedItem()));
    }


}
