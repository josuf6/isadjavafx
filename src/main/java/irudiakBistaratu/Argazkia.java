package irudiakBistaratu;

public class Argazkia {
    private String izena;
    private String fitxIzena;

    public Argazkia(String pIzen, String pFitxIzen) {
        this.izena = pIzen;
        this.fitxIzena = pFitxIzen;
    }

    public String getFitxIzena() {
        return this.fitxIzena;
    }

    @Override
    public String toString() { //irudiakBistaratu klasetik Argazkia motako objetu batekin toString() deia egiterakoan izena atributua bueltatzeko
        return this.izena;
    }
}
