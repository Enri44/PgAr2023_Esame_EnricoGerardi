import it.kibo.fp.lib.InputData;

import java.util.ArrayList;

/**
 * È la classe che gestisce tutto ciò che accade durante il viaggio del protagonista
 */
public class GestoreViaggio {

    /**
     * Stampa un saluto introduttivo per far capire al protagonista in che mondo si trova
     */
    public void stampaSaluto(){
        System.out.println("Benvenuto avventuriero in questo nuovo mondo!");
        System.out.println("Ti trovi in una mappa piena di pericoli e avventure.");
        System.out.println("La tua missione è sconfiggere il boss Cammo e vincere il gioco.");
        System.out.println("Parti con una vita di 20 e un attacco di 5.");
        System.out.println("Dovrai esplorare la mappa, scontrarti con i mostri e trovare la strada per il boss.");
        System.out.println("Hai a disposizione 10 tentativi per raggiungere il tuo obiettivo.");
        System.out.println("Ma attenzione! Questo mondo sa essere mutevole e non è detto che rimanga lo stesso in due tentativi diversi.");
        System.out.println("Buona fortuna e che la tua avventura inizi!\n");
    }

    /**
     * rimuove il collegamento reciproco di due nodi
     * @param n1 il primo
     * @param n2 il secondo
     */
    public void rimuoviCollegamento(Nodo n1, Nodo n2){
        n1.getCollegamenti().remove((Integer)n2.getId());
        n2.getCollegamenti().remove((Integer)n1.getId());
    }

    /**
     * Stampa i nodi dove è possibile spostarsi e ne fa scegliere uno
     * Dopo 5 tentativi di battere il gioco arriva l'aiuto di Dijkstra
     * @param protagonista il giocatore
     * @param mappa per muovere il protagonista
     */
    public void scegliPercorso(Giocatore protagonista, ArrayList<Nodo> mappa) {
        ArrayList<Integer> collegamenti = protagonista.getPosizione().getCollegamenti();
        System.out.println("\nSei nella città numero " + protagonista.getPosizione().getId() + ".");

        if(collegamenti.size()<=0) {
            protagonista.modificaVita(-1000000);
            System.out.println("\nGiocatore " + protagonista.getNome() + " Sei arrivato in un vicolo cieco, perciò la tua avventura finirà qui, peccato.");
            return;
        }
        System.out.println("Puoi muoverti alle seguenti città:");
        for (int i = 0; i < collegamenti.size(); i++) {
            System.out.println((i + 1) + "\tCittà " + collegamenti.get(i) + ".\n");
        }
       //Dijkstra l'animaletto ti aiuta solo se sei sceso sotto i 5 tentativi rimasti
        if(protagonista.tentativi<=5)
            consiglioDijkstra(protagonista.getPosizione(), mappa.get(mappa.size()-1), mappa);

        int scelta;
        do {
            scelta = InputData.readInteger("\nGiocatore " + protagonista.getNome() + " Inserisci il numero del nodo in cui vuoi muoverti: ");
        } while (scelta < 1 || scelta > collegamenti.size());

        int idProssimoNodo = collegamenti.get(scelta-1);
        rimuoviCollegamento(protagonista.getPosizione(), mappa.get(idProssimoNodo-1));
        protagonista.setPosizione(mappa.get(idProssimoNodo-1));
    }

    /**
     * Gestisce un breve combattimento a turni tra il protagonista e il mostro
     * che ha incontrato e vince l'ultimo che rimane con un po' di vita
     * @param giocatore chi gioca
     * @param mostro quello che si trovava sul nodo dove ci si è spostati
     */
    public static void combattimento(Giocatore giocatore, Mostro mostro) {
        if (mostro.getClass().equals(Cammo.class))
            System.out.println("\nAttento giocatore " + giocatore.getNome() + " , sei di fronte al temibile Cammo! è l'ora di giocarti il tutto per tutto in questa epica battaglia all'ultimo sangue!");
        else {
            System.out.println("è apparso un mostro selvatico e non sembra avere buone intenzioni.");
        }
        System.out.println("Inizia il combattimento!");

        while (giocatore.getVita() > 0 && mostro.getVita() > 0) {
            // Turno del giocatore
            System.out.println("È il tuo turno giocatore " + giocatore.getNome() +".");
            mostro.modificaVita(-giocatore.getAttacco());
            System.out.println("Hai attaccato il mostro e inflitto " + giocatore.getAttacco() + " danni.");

            if (mostro.getVita() <= 0) {
                System.out.println("Il mostro è stato sconfitto!");
                break;
            }

            // Turno del mostro
            System.out.println("È il turno del mostro.");
            giocatore.modificaVita(-mostro.getAttacco());
            System.out.println("Il mostro attacca il giocatore " + giocatore.getNome() +" e infligge " + mostro.getAttacco() + " danni.");

            if (giocatore.getVita() <= 0) {
                System.out.println("Il giocatore " + giocatore.getNome() + " è stato sconfitto!");
                break;
            }
        }
    }

    /**
     * @param giocatore di cui si controlla la vita
     * @return vero se il giocatore non ha più vita rimasta
     */
    public boolean gameOver(Giocatore giocatore) {
        return giocatore.getVita() <= 0;
    }

    /**
     * Controlla se il giocatore ha tentativi rimasti, se è morto
     * e vuole continuare ancora e se ha battuto il boss
     * @param giocatore di cui si controllano le condizioni
     * @return falso se il giocatore ha vinto, non ha più tentativi
     *         o non vuole più giocare e vero se vuole giocare
     */
    public boolean esito(Giocatore giocatore){
        if(giocatore.tentativi==1)
            return false;
        else if(gameOver(giocatore)){
            giocatore.tentativi--;
            System.out.println("\n\nOh no giocatore " + giocatore.getNome() +" hai perso. Hai " + giocatore.tentativi + " tentativi rimasti");
            return InputData.readYesOrNo("Vuoi provarci ancora una volta");
        }
        else{
            System.out.println("\n\nComplimentiii!! Hai completato il gioco e battuto il temibile Cammo! Ora puoi finalmente tornare nel tuo mondo... per ora.");
            return false;
        }
    }

    /**
     * Raggruppa tutti i metodi della classe e fa andare avanti la partita
     * finché il giocatore non vuole o non può più giocare
     */
    public void partita(){
        stampaSaluto();
        Giocatore protagonista = new Giocatore();
        boolean esito = true;
        while(esito){
            protagonista.modificaVita(20);
            protagonista.modificaAttacco(5);
            GestoreMappe archivio = new GestoreMappe();
            ArrayList<Nodo> mappa = archivio.generazioneMappaDiGioco();
            archivio.generaEventi(mappa);
            protagonista.setPosizione(mappa.get(0));
            System.out.println("\nTi trovi ora nella città iniziale.");
            while(!protagonista.getPosizione().equals(mappa.get(mappa.size()-1))){
                scegliPercorso(protagonista, mappa);
                if(gameOver(protagonista))break;
                if(protagonista.getPosizione().getMostro() != null){
                    combattimento(protagonista, protagonista.getPosizione().getMostro());
                    if(gameOver(protagonista))break;
                }
                else if(protagonista.getPosizione().getModVita()!= -100) {
                    protagonista.applicaBuffDebuff();
                    if (gameOver(protagonista)) break;
                }
            }
            esito = esito(protagonista);
        }
    }

    /**
     * Serve solo per facilitare Dijkstra
     * @param mappa da dove leggere i nodi
     * @return il nodo col peso minore di tutta la mappa
     */
    public Nodo nodoMinore(ArrayList<Nodo> mappa){
        if(mappa.size()==0)
            return null;

        Nodo minore=mappa.get(0); //imposta la prima città come quella minore

        if(mappa.size()==1)
            return minore;

        for(Nodo n : mappa)
            if(n.getPeso()<minore.getPeso())
                minore = n;
        return minore;
    }

    /**
     * Implementazione dell'algoritmo di Dijkstra che genera prima il percorso
     * più breve per raggiungere l'arrivo dalla partenza e poi stampa l'esito
     * sotto forma di un consiglio da parte dell'animaletto di cui parla la consegna
     * @param partenza nodo di partenza
     * @param arrivo nodo di arrivo
     * @param mappa da cui leggere i nodi
     */
    public void consiglioDijkstra(Nodo partenza, Nodo arrivo, ArrayList<Nodo> mappa){
        ArrayList<Nodo>nodi=(ArrayList<Nodo>) mappa.clone();
        partenza.setPeso(0); //imposta a 0 il peso cumulativo del nodo di partenza


        while(!nodi.isEmpty()) {

            Nodo nodoMinore = nodoMinore(nodi);

            nodi.remove(nodoMinore);

            // scorri tutti i nodi adiacenti a nodoMinore
            for(Integer id : nodoMinore.getCollegamenti()) {

                // se il nuovo peso cumulativo è minore di quello già presente, aggiornalo con quello calcolato
                if(nodoMinore.getPeso()+1 < mappa.get(id-1).getPeso()) {
                    mappa.get(id-1).setPeso(nodoMinore.getPeso()+1);
                    mappa.get(id-1).setNodoPrecedente(nodoMinore);
                }
            }
            if(nodoMinore == arrivo) // se si è giunti all'arrivo, si termina prima l'algoritmo
                break;

        }

        ArrayList<Nodo> percorso = new ArrayList<>();
        /*
         * Scorre tutte le città dall'arrivo alla partenza sfruttando l'attributo nodoPrecedente
         * e crea la lista delle città che compongono il percorso
         */
        Nodo tappa = arrivo;
        do {
            percorso.add(0,tappa); // aggiungi la tappa corrente all'inizio del percorso
            tappa = tappa.getNodoPrecedente();
        }while(percorso.get(0) != partenza);

        System.out.println("\nSei fortunato giocatore, il piccolo Dijkstra sembra volerti aiutare a scegliere la prossima città.\nSembra che gli piaccia particolarmente la città numero " + percorso.get(1).getId());
    }
}
