package src.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import src.application.models.Education;
import src.application.models.Exam;
import src.storage.Storage;

import java.time.LocalDate;

public class AfvikelEksamenWindow extends Stage {

    public AfvikelEksamenWindow(Education education){
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        this.setTitle("Afvikling");
        GridPane pane = new GridPane();
        initContent(pane,education);
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }


    private TextField terminTextField = new TextField();
    private DatePicker startTidDatePicker = new DatePicker();
    private DatePicker slutTidDatePicker = new DatePicker();
    private TextField stoptestTextField = new TextField();
    private ComboBox<Exam> examComboBox = new ComboBox<>();


    private void initContent(GridPane pane, Education education){
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label terminLabel = new Label("Termin:");
        pane.add(terminLabel,0,0);

        Label startTidLabel = new Label("StartTid:");
        pane.add(startTidLabel,0,1);

        Label slutTidLabel = new Label("SlutTid:");
        pane.add(slutTidLabel,0,2);

        Label stoptestLabel = new Label("Stoptest:");
        pane.add(stoptestLabel,0,3);

        Label examLabel = new Label("Exam:");
        pane.add(examLabel,0,4);

        pane.add(terminTextField,1,0);
        pane.add(startTidDatePicker,1,1);
        pane.add(slutTidDatePicker,1,2);
        pane.add(stoptestTextField,1,3);
        examComboBox.getItems().setAll(Storage.getExamsOnEducation(education));
        pane.add(examComboBox,1,4);

        Button afvikelButton = new Button("Afvikel");
        pane.add(afvikelButton,1,5);
        afvikelButton.setOnAction(e -> opretAfvikling());
        Button afbrydButton = new Button("Afbryd");
        pane.add(afbrydButton,2,5);
        afbrydButton.setOnAction(e -> this.close());
    }

    private void opretAfvikling(){
        String termin = terminTextField.getText();
        LocalDate startTime = startTidDatePicker.getValue();
        LocalDate slutTime = slutTidDatePicker.getValue();
        boolean stopTest = Boolean.parseBoolean(stoptestTextField.getText());
        Exam exam = examComboBox.getValue();

        Storage.createExamination(termin, startTime, slutTime, stopTest, exam);

        this.close();
    }




}
