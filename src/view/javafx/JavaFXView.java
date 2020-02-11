package view.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXView extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MVVM Demo JFX");

        Parent root = FXMLLoader.load(getClass().getResource("MVVMDemoJFX.fxml"));
        Scene scene = new Scene(root, 400, 312);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
