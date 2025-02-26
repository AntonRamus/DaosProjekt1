package src.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import src.application.models.Education;
import src.application.models.Student;

public class OpretEksamensForsøgWindow extends Stage {

    public OpretEksamensForsøgWindow(Student student){
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        this.setTitle("Afvikling");
        GridPane pane = new GridPane();
        initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);


    }
}
