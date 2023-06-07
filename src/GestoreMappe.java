import java.util.ArrayList;

public class GestoreMappe {

    ArrayList<Nodo> mappa1 = new ArrayList<>();
    ArrayList<Nodo> mappa2 = new ArrayList<>();
    ArrayList<Nodo> mappaBase = new ArrayList<>();

    public void generaMappaBase(){
        for(int i=1; i<=7; i++)
            mappaBase.add(new Nodo(i));
        mappaBase.get(0).aggiungiCollegamento(2);
        mappaBase.get(1).aggiungiCollegamento(3);
        mappaBase.get(1).aggiungiCollegamento(4);
        mappaBase.get(2).aggiungiCollegamento(5);
        mappaBase.get(3).aggiungiCollegamento(5);
        mappaBase.get(4).aggiungiCollegamento(6);
        mappaBase.get(5).aggiungiCollegamento(7);
    }

    public void generaMappeXML(){
        Reader.leggi_mappe(mappa1, mappa2);
    }
    public void stampaMappa(ArrayList<Nodo> mappa){
        for(Nodo n : mappa)
            System.out.println(n);
    }

    public ArrayList<Nodo> getMappa1() {
        return mappa1;
    }

    public void setMappa1(ArrayList<Nodo> mappa1) {
        this.mappa1 = mappa1;
    }

    public ArrayList<Nodo> getMappa2() {
        return mappa2;
    }

    public void setMappa2(ArrayList<Nodo> mappa2) {
        this.mappa2 = mappa2;
    }

    public ArrayList<Nodo> getMappaBase() {
        return mappaBase;
    }

    public void setMappaBase(ArrayList<Nodo> mappaBase) {
        this.mappaBase = mappaBase;
    }
}
