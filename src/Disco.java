import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Disco extends Thread{
    @Override
    public void run() {


        /**
         * Seta o formato que saíra a data e a hora.
         * **/
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        while (true) {

            try(FileWriter fw = new FileWriter("hora.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw))
            {
                LocalDateTime now = LocalDateTime.now();
                out.println(dtf.format(now));
                System.out.println("Disco: dei uma volta.");
                Thread.sleep(2500);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
