import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        Processador p = new Processador();
        Teclado t = new Teclado();
        Disco d = new Disco();
        Backup b = new Backup();


        p.start();
        t.start();
        d.start();
        b.start();

    }
}
