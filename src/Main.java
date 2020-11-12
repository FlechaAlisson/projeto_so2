import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1,false);


        Processador p = new Processador(semaphore);
        Teclado t = new Teclado();
        Disco d = new Disco(semaphore);


        File horatxt = new File("./hora.txt");
        if (!horatxt.exists()) {
            try {
                horatxt.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        p.start();
        t.start();
        d.start();

    }
}
