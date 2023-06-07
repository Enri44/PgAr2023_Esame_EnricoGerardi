import java.util.ArrayList;

/**
 * Un nodo è una tappa della missione e tutte le mappe
 * del programma sono ArrayLIst di Nodo
 */
public class Nodo {
    private final int id;
    private ArrayList<Integer> collegamenti;//gli id di tutti i nodi collegati al nodo in questione
    private Mostro mostro = null;//non è più null se ci viene posizionato un mostro
    private int modVita = -100;//è il valore iniziale in base al quale so se l'isola ha un modificatore o no. Poi viene sovrascritto
    private int modAtk = 0;
    private int peso = (int)Double.POSITIVE_INFINITY;//peso che serve solo per Dijkstra
    private Nodo nodoPrecedente;//variabile per memorizzare il nodo precedente che serve solo per Dijkstra

    public Nodo(int id) {
        this.id = id;
        this.collegamenti = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getCollegamenti() {
        return collegamenti;
    }

    public void aggiungiCollegamento(int idNodo) {
        collegamenti.add(idNodo);
    }

    public int getModVita() {
        return modVita;
    }

    public void setModVita(int modVita) {
        this.modVita = modVita;
    }

    public int getModAtk() {
        return modAtk;
    }

    public void setModAtk(int modAtk) {
        this.modAtk = modAtk;
    }

    public Mostro getMostro() {
        return mostro;
    }

    public void setMostro(Mostro mostro) {
        this.mostro = mostro;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Nodo getNodoPrecedente() {
        return nodoPrecedente;
    }

    public void setNodoPrecedente(Nodo nodoPrecedente) {
        this.nodoPrecedente = nodoPrecedente;
    }

    /**
     * Override del toString che stampa l'd di un Nodo e i suoi
     * collegamenti. Serviva per alcuni test
     * @return la stringa con le informazioni
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        if(collegamenti.size()!=0){
            sb.append("Collegamenti: ");
            for (int id : collegamenti) {
                sb.append(id).append(" ");
            }
        }
        return sb.toString();
    }

}

