package liburuenInfo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class LiburuenInfo extends Application {

    private class ComboElem {
        private String izenburua;
        private String ISBN;

        public ComboElem(String pIzenb, String pISBN) {
            this.izenburua = pIzenb;
            this.ISBN = pISBN;
        }

        public String getIzenburua() {
            return this.izenburua;
        }

        public String getISBN() {
            return this.ISBN;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Liburuak");
        primaryStage.setResizable(false);

        ComboBox comboLiburuak = new ComboBox<>();
        ObservableList<ComboElem> liburuList = FXCollections.observableArrayList();
        liburuList.addAll(
                new ComboElem("Blockchain: Blueprint for a New Economy", "9781491920497"),
                new ComboElem("R for Data Science", "1491910399"),
                new ComboElem("Fluent Python", "1491946008"),
                new ComboElem("Natural Language Processing with PyTorch", "1491978236"),
                new ComboElem("Data Algorithms", "9781491906187")
        );
        comboLiburuak.setItems(liburuList);
        comboLiburuak.setEditable(false);
        comboLiburuak.setConverter(new StringConverter<ComboElem>() {
            @Override
            public String toString(ComboElem comboElem) {
                if (comboElem == null)
                    return "";
                else
                    return comboElem.getIzenburua();
            }

            @Override
            public ComboElem fromString(String izena) {
                return null;
            }
        });

        VBox vbox = new VBox(comboLiburuak);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox,350,100);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
