package ehu.isad;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TxanponenPrezioa extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Prezioak");
        primaryStage.setResizable(false);

        Txanpona nireTxanpona = new Txanpona();

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().add("BTC");
        comboBox.getItems().add("ETH");
        comboBox.getItems().add("LTC");
        comboBox.setEditable(false);
        comboBox.setValue("BTC");

        Label label = new Label("1 " + comboBox.getValue() + "=" + nireTxanpona.prezioaLortu(comboBox.getValue()) + "€");

        comboBox.setOnAction(event -> label.setText("1 " + comboBox.getValue() + "=" + nireTxanpona.prezioaLortu(comboBox.getValue()) + "€"));

        VBox vbox = new VBox(label, comboBox);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox,200,80);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
