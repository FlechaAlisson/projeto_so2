import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1,false);


        Processador p = new Processador(semaphore);
        Teclado t = new Teclado();
        Disco d = new Disco(semaphore);
        Backup b = new Backup();



        p.start();
        t.start();
        d.start();
        b.start();

    }
}
