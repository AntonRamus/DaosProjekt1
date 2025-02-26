package src.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
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
        GridPane gridPane = new GridPane();
        initContent(gridPane);
        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(GridPane gridPane) {
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);


        HBox topHbox = new HBox();
        topHbox.setSpacing(10);
        gridPane.add(topHbox,0,0);

        ComboBox<Education> uddannelseComboBox = new ComboBox<>();
        uddannelseComboBox.setOnAction(e->updateUddannelseComboBox(uddannelseComboBox));
        topHbox.getChildren().add(uddannelseComboBox);

        ComboBox<Exam> eksamenComboBox = new ComboBox<>();
        eksamenComboBox.setOnAction(e->updateEksamenComboBox(eksamenComboBox, uddannelseComboBox));
        topHbox.getChildren().add(eksamenComboBox);

        VBox leftVBox = new VBox();
        leftVBox.setSpacing(10);
        gridPane.add(leftVBox,0,1);

        ListView<Student> studentListView = new ListView<>();
        studentListView.getItems().setAll();
        leftVBox.getChildren().add(studentListView);

        VBox rightVBox = new VBox();
        rightVBox.setSpacing(10);
        gridPane.add(rightVBox,1,0);

        Button button1 = new Button("Button1");
        button1.setOnAction(null);
        rightVBox.getChildren().add(button1);

        Button button2 = new Button("Button2");
        button2.setOnAction(null);
        rightVBox.getChildren().add(button2);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        gridPane.add(hBox,0,2);

        Button button3 = new Button("Afvikel Eksamen");
        button3.setOnAction(event-> afvikelEksamen(uddannelseComboBox.getSelectionModel().getSelectedItem()));
        hBox.getChildren().add(button3);

        Button button4 = new Button("Opret Eksamens Forsøg");
        button4.setOnAction(e->opretEksamensForsøgAction(null));
        hBox.getChildren().add(button4);

    }

    private void afvikelEksamen(Education education){
        if (education!=null){
        new AfvikelEksamenWindow(education).showAndWait();
        }
    }

    private void opretEksamensForsøgAction(Student student){
        if (student!=null) {
            new OpretEksamensForsøgWindow(student).showAndWait();
        }
    }

    private void updateUddannelseComboBox(ComboBox<Education> comboBox){
        comboBox.getItems().setAll(Storage.getEducations());
    }

    private void updateEksamenComboBox(ComboBox<Exam> comboBox1, ComboBox<Education> comboBox2){
        comboBox1.getItems().setAll(Storage.getExamsOnEducation(comboBox2.getSelectionModel().getSelectedItem()));
    }
}
