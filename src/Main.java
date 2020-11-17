import java.io.File;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1,false);


        Processador p = new Processador(semaphore);
        Teclado t = new Teclado();
        Disco d = new Disco(semaphore);


        /*
        * Checa se hora.txt existe
        * Se não existe, cria-o.
        * */
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
