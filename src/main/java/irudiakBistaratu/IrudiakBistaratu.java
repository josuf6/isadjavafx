package irudiakBistaratu;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IrudiakBistaratu extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Irudiak");
        primaryStage.setResizable(false);

        //comboBilduma (gorde moten izenak ComboBox batean)
        ComboBox<String> comboBilduma = new ComboBox<>();
        comboBilduma.setEditable(false);
        List<String> bildumak = List.of("abereak", "landareak", "frutak");
        ObservableList<String> bildumaList = FXCollections.observableArrayList(bildumak);
        comboBilduma.setItems(bildumaList);
        comboBilduma.getSelectionModel().selectFirst(); //Defektuz, programa hasieratzean, comboBilduma-n abereak aukeratzeko

        //bildumaMap (gorde mota bakoitzeko mota horretako objektuen zerrenda HashMap batean, gakoa motaren izena izanik)
        Map<String, List<Argazkia>> bildumaMap = new HashMap<>();
        bildumaMap.put("abereak", List.of(
                new Argazkia("Elefantea", "elefantea.jpeg"),
                new Argazkia("Txakurra", "txakurra.jpeg"),
                new Argazkia("Untxia", "untxia.png")
        ));
        bildumaMap.put("landareak", List.of(
                new Argazkia("Kaktusa", "cactus.png"),
                new Argazkia("Hostoa", "landareberdea.jpeg"),
                new Argazkia("Ekilorea", "landarehoria.jpeg")
        ));
        bildumaMap.put("frutak", List.of(
                new Argazkia("Marrubia", "fresa.jpeg"),
                new Argazkia("Sandia", "sandia.png"),
                new Argazkia("Sagarra", "sagarra.jpeg")
        ));

        //listViewOfArgazki (gorde mota bateko objektuak ListView batean)
        ObservableList<Argazkia> argazkiList = FXCollections.observableArrayList(bildumaMap.get("abereak"));
        ListView<Argazkia> listViewOfArgazki = new ListView<>(argazkiList);
        listViewOfArgazki.setMaxSize(400,74);

        //listViewOfArgazki-ko elementu bakoitzaren listener-a (imageViewArgazki-n elementu horren irudia jartzea)
        ImageView imageViewArgazki = new ImageView();
        listViewOfArgazki.getSelectionModel().selectedItemProperty().addListener( ((observable, oldValue, newValue) -> {
            if (observable.getValue() == null) return;
            String fitx = observable.getValue().getFitxIzena();
            imageViewArgazki.setImage(lortuIrudia(fitx));
        }));
        listViewOfArgazki.getSelectionModel().selectFirst(); //Defektuz, programa hasieratzean, listViewOfArgazki-ko lehenengo elementua aukeratzeko

        //comboBilduma-n aukeratuta dagoen elementua aldatzean listViewOfArgazki-n dagozkion elementuak jartzeko
        comboBilduma.setOnAction(e -> { //argazkiList-en balioak aldatu (listViewOfArgazki-n aldatzen dira ere)
            argazkiList.clear();
            argazkiList.addAll(bildumaMap.get(comboBilduma.getValue())); //comboBilduma-n aukeratuta dagoen elementuarekin erlazionatuta dauden elementuak gehitu
            listViewOfArgazki.getSelectionModel().selectFirst(); //comboBilduma-ren balioa aldatzean listViewOfArgazki-ko lehenengo elementua aukeratzeko
        });

        //Lehio bat sortutako elementuekin egin eta bistaratzeko
        VBox vbox = new VBox(comboBilduma, listViewOfArgazki, imageViewArgazki);
        vbox.setAlignment(Pos.TOP_LEFT);
        Scene scene = new Scene(vbox,400,147);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    private Image lortuIrudia(String fitx) {
        InputStream is = getClass().getResourceAsStream("/" + fitx);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SwingFXUtils.toFXImage(bi, null);
    }
}
