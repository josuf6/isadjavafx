package kautotuSceneBuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Kontrolatzailea {

    @FXML
    private Label lblErabiltzailea;

    @FXML
    private TextField txtErabiltzailea;

    @FXML
    private Label lblPasahitza;

    @FXML
    private PasswordField psswdPasahitza;

    @FXML
    private Button btnKautotu;

    private Kautotu main;

    public void setMainApp(Kautotu mainApp) {
        this.main = mainApp;
    }

    @FXML
    void onClick(ActionEvent event) {
        System.out.println("Erabiltzailea: " + txtErabiltzailea.getText());
        System.out.println("Pasahitza: " + psswdPasahitza.getText());
        txtErabiltzailea.clear();
        psswdPasahitza.clear();
    }
}
