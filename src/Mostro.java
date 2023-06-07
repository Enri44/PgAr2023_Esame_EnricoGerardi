import it.kibo.fp.lib.RandomDraws;

/**
 * Implementa Creatura e basta
 */
public class Mostro implements Creatura {
        protected int vita;
        protected int attacco;

    /**
     * I valori inseriti variano da mostro a mostro come voleva la consegna
     */
        public Mostro() {
            this.vita = 12 + RandomDraws.drawInteger(-5, 5);
            this.attacco = 3 + RandomDraws.drawInteger(-2, 2);
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
    }
