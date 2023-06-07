/**
 * Questa interfaccia è la base per definire le classi Giocatore e Mostro che hanno diverse caratteristiche in comune
 */
public interface Creatura {
        int getVita();
        int getAttacco();
        void modificaVita(int valore);
    }
