import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

public class Disco extends Thread{
    Semaphore s;
    public Disco(Semaphore semaphore) {
        s = semaphore;
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {


        /**
         * Seta o formato que saíra a data e a hora.
         * **/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        while (true) {

            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Disco: Liberado para dar uma volta");
            try(FileWriter fw = new FileWriter("hora.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                LocalDateTime now = LocalDateTime.now();
                out.println(dtf.format(now));

                File dir = new File("./backup");

                for (File f:dir.listFiles()) {
                    out.println(f.getName());
                }
                out.println("==============");
                System.out.println("Disco: dei uma volta.");


                out.close();
                bw.close();
                fw.close();

                Thread.sleep(2500);

            } catch (IOException | InterruptedException e) {
                System.out.println("Disco: Mas o arquivo já estava sendo usado em outro processo");
            }

        }
    }
}
