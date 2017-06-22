interface Buffer {
       public void set(int value);
       public int get();
}

class SynchronizedBuffer implements Buffer {

       private int buffer[] = {-1, -1, -1};
       private int occupiedSlot = 0;  //variabile che ci consente di determinare se il consumatore ha già consumato 

      private int readLocation = 0;
      private int writeLocation = 0;

       public synchronized void set(int value) {
           System.out.println(Thread.currentThread().getName());

           while (occupiedSlot == buffer.length) {
                 //il consumatore non ha ancora liberato il buffer
                 System.out.println(" ha provato a produrre");
                 System.out.println("Il buffer è pieno. In attesa.");

                 try {
                     wait();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
              }

              buffer[writeLocation] = value;
              ++occupiedSlot;
              System.out.println(" ha prodotto: " + buffer[writeLocation]);
              writeLocation = (writeLocation + 1) % buffer.length;

              notify();
       }

       public synchronized int get() {
              System.out.println(Thread.currentThread().getName());

              while(occupiedSlot == 0) {
                   //buffer vuoto
                   System.out.println(" ha provato a consumare.");
                   System.out.println("Buffer vuoto. Aspetta");

                   try {
                       wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
             }

             int read = buffer[readLocation];

             System.out.println(" ha consumato: " + read);
             --occupiedSlot;
             readLocation = (readLocation + 1) % buffer.length;
             notify();

             return read;
       }
}

class Produttore extends Thread {
    private Buffer buffer;

    public Produttore(Buffer sharedBuffer) {
        buffer = sharedBuffer;
        Thread.currentThread().setName("Produttore");
    }

    public void run() {
        for (int i=0; i<5; i++) {
            try {
                Thread.sleep((int)Math.random() * 3001);
                buffer.set(i);
                System.out.println("ha prodotto: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumatore extends Thread {
    private Buffer buffer;
    private int somma = 0;

    public Consumatore(Buffer sharedBuffer) {
        buffer = sharedBuffer;
        Thread.currentThread().setName("Consumatore");
    }

    public void run(){
        for (int i=0; i<5; i++){
            try{
                Thread.sleep((int)Math.random() * 3001);
                somma += buffer.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "ha consumato: " + somma);
    }
}

public class CircularBuffer {
    public static void main (String[] args){
        SynchronizedBuffer buffer = new SynchronizedBuffer();

        Produttore produttore1 = new Produttore (buffer);
        Consumatore consumatore1 = new Consumatore(buffer);

        produttore1.start();
        consumatore1.start();
    }
}
