import it.kibo.fp.lib.RandomDraws;

import java.util.ArrayList;

public class GestoreViaggio {
    private ArrayList<Nodo> mappa = new ArrayList<>();

    public ArrayList<Nodo> getMappa() {
        return mappa;
    }

    public void generaEventi(){
        for(Nodo n : mappa){
            if(n.getId()==1){}
            else if(n.getId()==mappa.size()){
                //mostro = new Cammo;
            }
            else{
                int scelta = RandomDraws.drawInteger(1,2);
                if(scelta==1){}//mostro = new Mostro
                else{
                    n.setModVita(RandomDraws.drawInteger(-5, 10));
                    n.setModAtk(RandomDraws.drawInteger(-3, 3));
                }
            }
        }
    }



}
