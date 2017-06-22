import java.util.ArrayList;
class Bacchetta {
     int[] bacchette = {-1,-1,-1,-1,-1};
    boolean[] bacchetteOccupate = {false,false,false,false,false};

    public synchronized void prendiBacchette(int posizione){
            if(posizione == 0) {
                if(bacchetteOccupate[posizione] && bacchetteOccupate[4]){
                    try {
                        System.out.println("Le bacchette sono occupate. Aspetta");
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }else{
                    //le bacchette sono libere
                    bacchette[0] = 0;
                    bacchette[4] = 0;
                    bacchetteOccupate[0] = true;
                    bacchetteOccupate[4] = true;
                }
                }else{
                if(bacchetteOccupate[posizione] && bacchetteOccupate[posizione-1]){
                    try {
                        System.out.println("Le bacchette sono occupate. Aspetta");
                        wait();
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }else{
                    //le bacchette sono libere
                    bacchette[posizione] = bacchette[posizione -1] = posizione;
                    bacchetteOccupate[posizione] = bacchetteOccupate[posizione-1] = true;
                }
            }
    }

    public synchronized void lasciaBacchette(int posizione) {
        if(posizione == 0){
            if(bacchette[posizione] == posizione && bacchette[4] == posizione){
                bacchette[posizione] = bacchette[4] = -1;
                bacchetteOccupate[posizione] = bacchetteOccupate[4] = false;

                notifyAll();
            }
         }else{
                if(bacchette[posizione] == posizione && bacchette[posizione-1] == posizione){
                    bacchette[posizione] = bacchette[posizione-1] = -1;
                    bacchetteOccupate[posizione] = bacchetteOccupate[posizione-1] = false;

                    notifyAll();
                }
         }
    }
}

 class Filosofo extends Thread {
    private int posizione;
    private Bacchetta bacchette;

    public Filosofo(int index, Bacchetta b){
        posizione = index;
        bacchette = b;
        Thread.currentThread().setName(Integer.toString(posizione));
    }

    public void mangia(){
        System.out.println("Filosofo " + posizione  + " vorrebbe mangiare. Prende le bacchette.");
        bacchette.prendiBacchette(posizione);
        try {
            System.out.println("Filosofo " + posizione + " Ha ottenuto le bacchete e sta mangiando.");
            Thread.sleep((int)Math.random() * 3001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Filosofo " + posizione + " Ha finito di mangiare. Lascia le bacchette.");
        bacchette.lasciaBacchette(posizione);
    }

    public void pensa(){
            try {
                System.out.println("Filosofo " + posizione + " sta pensando");
                Thread.sleep((int)Math.random() * 3001);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

        public void run(){
            while(true){
            mangia();
            pensa();
        }
    }

     public int getPosizione() {
         return posizione;
     }
}

 class Tavolo {
    private Bacchetta bacchette = new Bacchetta();
     private ArrayList<Filosofo> filosofi = new  ArrayList<Filosofo>();

    public Tavolo(){
        for(int i=0; i<5; i++){
            Filosofo filosofo = new Filosofo(i, bacchette);
            filosofi.add(filosofo);
        }
    }

    public Filosofo getFilosofo(int index) {
        return filosofi.get(index);
    }
}

public class filosofi{
    public static void main(String[] args) {
        Tavolo t = new Tavolo();

        for (int i=0; i<5; i++) {
            System.out.println(t.getFilosofo(i).getPosizione());
            t.getFilosofo(i).start();
        }
    }
}
