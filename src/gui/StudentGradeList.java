package src.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import src.application.models.Exam;
import src.application.models.Examination;
import src.storage.Storage;

//Gruppe 2 - Anton, Sidse og Victor

public class StudentGradeList extends Stage {
    private final Exam exam;
    private final Examination examination;

    public StudentGradeList(Exam exam, Examination examination) {
        this.exam = exam;
        this.examination = examination;
        this.setTitle("Student grade list");
        VBox vBox = new VBox(10);
        initContent(vBox);
        Scene scene = new Scene(vBox);
        this.setScene(scene);
    }

    private void initContent(VBox vBox) {
        vBox.setPadding(new Insets(20));
        ListView<String> listView = new ListView<>();
        Button oKButton = new Button("Ok");

        listView.getItems().setAll(Storage.getStudentGradesOnExamination(exam, examination));

        vBox.getChildren().addAll(listView, oKButton);
    }
}
