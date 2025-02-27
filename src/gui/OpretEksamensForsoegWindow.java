package src.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import src.storage.Storage;

import java.time.LocalDate;

//Gruppe 2 - Anton, Sidse og Victor

public class OpretEksamensForsoegWindow extends Stage {

    public OpretEksamensForsoegWindow(){
        setResizable(false);
        this.setTitle("Afvikling");
        GridPane pane = new GridPane();
        initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }
    private TextField studentID = new TextField();
    private TextField examniationID = new TextField();
    private TextField grade = new TextField();
    private DatePicker date = new DatePicker();

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPrefSize(350,200);

        Label studentIDLabel = new Label("Student ID:");
        Label examniationIDLabel = new Label("Eksamens ID:");
        Label gradeLabel = new Label("Karakter:");
        Label dateLabel = new Label("Dato:");

        pane.add(studentIDLabel,0,0);
        pane.add(examniationIDLabel,0,1);
        pane.add(gradeLabel,0,2);
        pane.add(dateLabel,0,3);

        pane.add(studentID,1,0);
        pane.add(examniationID,1,1);
        pane.add(grade,1,2);
        pane.add(date,1,3);

        Button Create = new Button("Opret");
        pane.add(Create,1,4);
        Create.setOnAction(e -> createExamTryAction());

        Button cancel = new Button("Cancel");
        pane.add(cancel,1,5);
        cancel.setOnAction(e -> this.close());




    }

    private void createExamTryAction() {
        int studentId = Integer.parseInt(studentID.getText());
        int examinationId = Integer.parseInt(examniationID.getText());
        String grade = this.grade.getText();
        LocalDate date = this.date.getValue();

        Storage.createExamTry(studentId, examinationId, grade, date);

        this.close();
    }
}
