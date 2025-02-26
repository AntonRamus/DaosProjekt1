package src.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    }
}
