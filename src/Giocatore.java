import it.kibo.fp.lib.InputData;

/**
 * Implementa l'interfaccia Creatura e aggiunge altre variabili e metodi utili
 */
public class Giocatore implements Creatura {
    private int vita;
    private int attacco;
    private Nodo posizione;//il nodo dove si trova il giocatore
    private final String nome;
    public int tentativi;//il numero di tentativi rimasti al giocatore prima di perdere automaticamente

    public Giocatore() {
        this.nome = InputData.readNonEmptyString("Qual è il tuo nome?", true);
        this.vita = 20;
        this.attacco = 5;
        this.tentativi=10;
    }

    @Override
    public int getVita() {
        return vita;
    }

    @Override
    public int getAttacco() {
        return attacco;
    }

    @Override
    public void modificaVita(int valore) {
        vita += valore;
    }

    public void modificaAttacco(int valore) {
        attacco += valore;
    }

    public String getNome() {
        return nome;
    }

    /**
     * In un colpo solo applica le modifiche del modificatore e ti aggiorna sulle condizioni del giocatore
     */
    public void applicaBuffDebuff(){
        System.out.println("\nOh! Hai trovato un modificatore!");
        modificaVita(posizione.getModVita());
        System.out.println("La tua vita è ora di " + vita);
        modificaAttacco(posizione.getModAtk());
        System.out.println("Il tuo attacco è ora di " + attacco);
    }

    public Nodo getPosizione() {
        return posizione;
    }

    public void setPosizione(Nodo posizione) {
        this.posizione = posizione;
    }
}

