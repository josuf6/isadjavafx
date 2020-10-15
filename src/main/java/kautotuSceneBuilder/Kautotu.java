package kautotuSceneBuilder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Kautotu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/kautotuSceneBuilder.fxml"));
        Parent root = (Parent) loader.load();

        Kontrolatzailea kontrolatzailea = loader.getController();
        kontrolatzailea.setMainApp(this);

        primaryStage.setTitle("Kautotu");
        primaryStage.setScene(new Scene(root, 300, 150));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
