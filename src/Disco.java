import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

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

    private void backup(){
        File dir = new File("./backup");
        int i = dir.listFiles().length;
        int ultimoBackup = 0;
        try{
            String name = dir.listFiles()[i - 1].getName();
            /**
             * Pega o ultimo digito do ultimo backup e incrementa ele
             * **/
            ultimoBackup = (int) name.charAt(6) - (int) '0' + 1;
        }catch (ArrayIndexOutOfBoundsException ignored){}



        if (i > 2) dir.listFiles()[0].delete();


        String path = "./backup/backup" + ultimoBackup + ".txt";

        try{
            Files.copy(Paths.get("./hora.txt"), Paths.get(path), REPLACE_EXISTING);
        }catch (IOException ignored){}

        System.out.println("Disco: backup feito!");
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

                backup();

                Thread.sleep(2500);


            } catch (IOException | InterruptedException e) {
            }

        }
    }
}
