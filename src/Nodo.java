import java.util.ArrayList;

public class Nodo {
    private int id;
    private ArrayList<Integer> collegamenti;
    //private Mostro mostro = null;
    private int modVita = 0;
    private int modAtk = 0;

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
    public void spawnMostro(){}

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
