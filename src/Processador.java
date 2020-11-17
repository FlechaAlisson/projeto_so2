import java.util.Random;
import java.util.concurrent.Semaphore;

public class Processador extends Thread {
    private final int[] memoria;
    private int pc = 0;
    Semaphore s;

    public Processador(Semaphore semaphore) {
        Random r = new Random();
        memoria = r.ints(500,0,1000).toArray();

        s = semaphore;
    }

    @Override
    public void run() {

        while (true){
            if(pc <= memoria.length - 1){
                System.out.println("Processador: executei instrução " + memoria[pc] + ". PC: " + pc);
                if (memoria[pc] % 5 == 0) s.release();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    pc++;
                }
            }else {
                System.out.println("Processador ocioso");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }
            }
        }
    }
}
