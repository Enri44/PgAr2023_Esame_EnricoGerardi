public class Main {
    public static void main(String[] args) {
        GestoreMappe g = new GestoreMappe();
        g.generaMappeXML();
        g.stampaMappa(g.getMappa1());
        g.stampaMappa(g.getMappa2());
    }
}