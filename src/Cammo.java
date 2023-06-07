import it.kibo.fp.lib.RandomDraws;

/**
 * La classe Cammo è il boss finale, perciò è un mostro con le statistiche un po' aumentate
 */
public class Cammo extends Mostro {
    public Cammo() {
        super(); // Richiama il costruttore della classe genitore (Mostro)
        this.vita = 18 + RandomDraws.drawInteger(-5, 5);
        this.attacco = 4 + RandomDraws.drawInteger(-2, 2);
    }
}

