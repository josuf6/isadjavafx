package ehu.isad;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class TxanponenPrezioa extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Prezioak");
        primaryStage.setResizable(false);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().add("BTC");
        comboBox.getItems().add("ETH");
        comboBox.getItems().add("LTC");
        comboBox.setEditable(false);
        comboBox.setValue("BTC");

        Label label = new Label("1 " + comboBox.getValue() + "=" + this.prezioaLortu(comboBox.getValue()) + "€");

        comboBox.setOnAction(event -> label.setText("1 " + comboBox.getValue() + "=" + this.prezioaLortu(comboBox.getValue()) + "€"));

        VBox vbox = new VBox(label, comboBox);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox,200,80);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private float prezioaLortu(String mota) {
        String emaitza = this.irakurriURL(mota);
        Gson gson = new Gson();
        Txanpona txanpon = gson.fromJson(emaitza, Txanpona.class);
        return txanpon.price;
    }

    private String irakurriURL(String mota) {
        String inputLine = "";
        URL url;

        try {
            url = new URL("https://api.gdax.com/products/" + mota.toLowerCase() + "-eur/ticker");
            URLConnection urlKonexioa = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlKonexioa.getInputStream()));
            inputLine = br.readLine();
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputLine;
    }
}
