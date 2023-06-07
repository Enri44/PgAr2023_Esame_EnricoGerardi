import it.kibo.fp.lib.RandomDraws;

import java.util.ArrayList;
import java.util.Collections;

/**
 * La classe che crea le mappe random e memorizza sia la mappa base sia le mappe degli xml
 */
public class GestoreMappe {
    ArrayList<Nodo> mappa1 = new ArrayList<>();//prima mappa dell'xml
    ArrayList<Nodo> mappa2 = new ArrayList<>();//seconda mappa dell'xml
    ArrayList<Nodo> mappaBase = new ArrayList<>();//mappa base

    /**
     * Memorizza su mappaBase la mappa disegnata alla lavagna durante l'esame
     */
    public void generaMappaBase(){//i collegamenti sono a coppie perché ciascun collegamento è valido in entrambi i versi
        for(int i=1; i<=7; i++)
            mappaBase.add(new Nodo(i));
        mappaBase.get(0).aggiungiCollegamento(2);
        mappaBase.get(1).aggiungiCollegamento(1);

        mappaBase.get(1).aggiungiCollegamento(3);
        mappaBase.get(2).aggiungiCollegamento(2);

        mappaBase.get(1).aggiungiCollegamento(4);
        mappaBase.get(3).aggiungiCollegamento(2);

        mappaBase.get(2).aggiungiCollegamento(5);
        mappaBase.get(4).aggiungiCollegamento(3);

        mappaBase.get(3).aggiungiCollegamento(5);
        mappaBase.get(4).aggiungiCollegamento(4);

        mappaBase.get(4).aggiungiCollegamento(6);
        mappaBase.get(5).aggiungiCollegamento(5);

        mappaBase.get(5).aggiungiCollegamento(7);
        mappaBase.get(6).aggiungiCollegamento(6);
    }

    /**
     * Memorizza le due mappe sull'xml nelle variabili della classe
     */
    public void generaMappeXML(){
        Reader.leggi_mappe(mappa1, mappa2);
    }
    /*
    public void stampaMappa(ArrayList<Nodo> mappa){
        for(Nodo n : mappa)
            System.out.println(n);
    }
    */

    /**
     * Genera una mappa con collegamenti casuali attraverso il metodo Collections.shuffle
     * Il primo e l'ultimo nodo hanno solo un collegamento col secondo e il penultimo nodo
     * della mappa per semplicità
     * @return la mappa generata casualmente
     */
    public ArrayList<Nodo> generaMappaCasuale() {
        ArrayList<Nodo> mappa = new ArrayList<>();

        // Creazione dei 15 nodi senza collegamenti
        for (int i = 1; i <= 15; i++) {
            Nodo nodo = new Nodo(i);
            mappa.add(nodo);
        }

        // Collegamento dei nodi di inizio e arrivo ad un solo altro nodo
        mappa.get(0).aggiungiCollegamento(2); // Nodo di inizio collegato al nodo successivo
        mappa.get(1).aggiungiCollegamento(1); // e viceversa
        mappa.get(13).aggiungiCollegamento(15); // Nodo di arrivo collegato al nodo precedente
        mappa.get(14).aggiungiCollegamento(14); // e viceversa
        // Creazione di un elenco di nodi (escludendo nodo di inizio e arrivo) per generare collegamenti casuali
        ArrayList<Nodo> nodiCasuali = new ArrayList<>(mappa.subList(1, 14));
        Collections.shuffle(nodiCasuali);
        // Collegamento casuale dei nodi tra loro
        for (int i = 0; i < nodiCasuali.size() - 1; i++) {
            Nodo nodoCorrente = nodiCasuali.get(i);
            Nodo nodoSuccessivo = nodiCasuali.get(i + 1);
            nodoCorrente.aggiungiCollegamento(nodoSuccessivo.getId());
            nodoSuccessivo.aggiungiCollegamento(nodoCorrente.getId());
        }
        return mappa;
    }

    /**
     * Tira una moneta per scegliere tra la mappa di base e la mappa casuale
     * @return la mappa con cui si gioca
     */
    public ArrayList<Nodo> generazioneMappaDiGioco(){
        int lancioMoneta= RandomDraws.drawInteger(1,2);
        ArrayList<Nodo> mappa = new ArrayList<>();
        if(lancioMoneta==1){
            generaMappaBase();
            mappa = mappaBase;
        }
        else {
            mappa = generaMappaCasuale();
        }
        return mappa;
    }

    /**
     * Data una mappa prende ciascun nodo e genera o un
     * mostro o un modificatore di statistiche su quel nodo
     * @param mappa la mappa da cui prendere i nodi
     */
    public void generaEventi(ArrayList<Nodo> mappa){
        for(Nodo n : mappa){
            if(n.getId()==1){}
            else if(n.getId()==mappa.size()){
                n.setMostro(new Cammo());
            }
            else{
                int scelta = RandomDraws.drawInteger(1,2);
                if(scelta==1){
                    n.setMostro(new Mostro());
                }
                else{
                    n.setModVita(RandomDraws.drawInteger(-5, 10));
                    n.setModAtk(RandomDraws.drawInteger(-3, 3));
                }
            }
        }
    }
}
