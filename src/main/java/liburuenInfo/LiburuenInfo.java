package liburuenInfo;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LiburuenInfo extends Application {

    Liburu liburu;
    Text izenburua;
    Text azpititulua;
    Text argitaletxeak;
    Text orriKop;
    VBox vBoxCombo;
    VBox vBoxIzenb;
    VBox vBoxAzpiTit;
    VBox vBoxArgit;
    VBox vBoxOrriKop;
    VBox vBoxNag;
    Scene scene;

    private class ComboElem {
        private final String izenburua;
        private final String ISBN;

        public ComboElem(String pIzenb, String pISBN) {
            this.izenburua = pIzenb;
            this.ISBN = pISBN;
        }

        public String getISBN() {
            return this.ISBN;
        }

        @Override
        public String toString() {
            return this.izenburua;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Liburuak");
        primaryStage.setResizable(false);

        //comboLiburuak
        ObservableList<ComboElem> liburuList = FXCollections.observableArrayList();
        liburuList.addAll(
                new ComboElem("Blockchain: Blueprint for a New Economy", "9781491920497"),
                new ComboElem("R for Data Science", "1491910399"),
                new ComboElem("Fluent Python", "1491946008"),
                new ComboElem("Natural Language Processing with PyTorch", "1491978236"),
                new ComboElem("Data Algorithms", "9781491906187")
        );
        ComboBox<ComboElem> comboLiburuak = new ComboBox<>(liburuList);
        comboLiburuak.setEditable(false);

        //Testuak sortu
        izenburua = new Text();
        izenburua.setWrappingWidth(326);
        azpititulua = new Text();
        azpititulua.setWrappingWidth(326);
        argitaletxeak = new Text();
        argitaletxeak.setWrappingWidth(326);
        orriKop = new Text();
        orriKop.setWrappingWidth(326);

        //comboLiburuak objektuaren listenerra
        comboLiburuak.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (observable.getValue() == null) return;
            String pISBN = observable.getValue().getISBN();
            this.infoLortu(pISBN);
            this.testuaEguneratu();
        });
        comboLiburuak.getSelectionModel().selectFirst(); //programa hasieratzean, defektuz, comboLiburuak objektuaren lehenengo elementua aukeratzeko (hemen listenerra erabiltzen denez, listenerra eta gero jarri)

        //leihoaren ezarpenak
        vBoxCombo = new VBox(comboLiburuak);
        vBoxCombo.setAlignment(Pos.TOP_CENTER);
        vBoxCombo.setPadding(new Insets(10, 0, 5, 0));
        vBoxIzenb = new VBox(izenburua);
        vBoxIzenb.setAlignment(Pos.TOP_LEFT);
        vBoxIzenb.setPadding(new Insets(5, 0, 2, 0));
        vBoxAzpiTit = new VBox(azpititulua);
        vBoxAzpiTit.setAlignment(Pos.TOP_LEFT);
        vBoxAzpiTit.setPadding(new Insets(2, 0, 2, 0));
        vBoxArgit = new VBox(argitaletxeak);
        vBoxArgit.setAlignment(Pos.TOP_LEFT);
        vBoxArgit.setPadding(new Insets(2, 0, 2, 0));
        vBoxOrriKop = new VBox(orriKop);
        vBoxOrriKop.setAlignment(Pos.TOP_LEFT);
        vBoxOrriKop.setPadding(new Insets(2, 0, 0, 0));
        vBoxNag = new VBox(vBoxCombo, vBoxIzenb, vBoxAzpiTit, vBoxArgit, vBoxOrriKop);
        vBoxNag.setAlignment(Pos.TOP_CENTER);
        vBoxNag.setPadding(new Insets(0, 10, 0, 10));

        scene = new Scene(vBoxNag, 346, 160);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private void infoLortu(String pISBN) {
        String emaitza = this.irakurriURL(pISBN);
        Gson gson = new Gson();
        liburu = gson.fromJson(emaitza, Liburu.class);
    }

    private String irakurriURL(String pISBN) {
        String inputLine = "";
        URL url;
        try {
            url = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:" + pISBN + "&jscmd=details&format=json");
            URLConnection urlKonexioa = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlKonexioa.getInputStream()));
            inputLine = br.readLine();
            br.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //details:{}-en giltzen artean dagoen testua (giltzak barne, Json formatua dela eta) inputLine-n gorde
        String[] zatiak = inputLine.split("details\":");
        inputLine = zatiak[1];
        zatiak = inputLine.split(", \"preview\":");
        inputLine = zatiak[0];
        return inputLine;
    }

    private void testuaEguneratu() {
        this.izenburua.setText("Izenburua: " + this.tituluaKudeatu(liburu.getTitle()));
        this.azpititulua.setText("Azpititulua: " + this.azpitituluaKudeatu(liburu.getSubtitle()));
        this.argitaletxeak.setText("Argitaletxea(k): " + this.argitaletxeakKudeatu(liburu.getPublishers()));
        this.orriKop.setText("Orri kopurua: " + this.orriKopKudeatu(liburu.getNumber_of_pages()));
    }

    private String tituluaKudeatu(String tit) {
        if (tit == null) return "";
        else return tit;
    }

    private String azpitituluaKudeatu(String azpiTit) {
        if (azpiTit == null) return "";
        else return azpiTit;
    }

    private String argitaletxeakKudeatu(String[] argitaletxeak) {
        StringBuilder emaitza = new StringBuilder();
        if (argitaletxeak.length > 0) {
            emaitza = new StringBuilder(argitaletxeak[0]);
            int i = 1;
            while (i < argitaletxeak.length) {
                emaitza.append(", ").append(argitaletxeak[i]);
                i++;
            }
        }
        return emaitza.toString();
    }

    private String orriKopKudeatu(int number_of_pages) {
        if (number_of_pages == 0) return "";
        else return Integer.toString(number_of_pages);
    }
}
