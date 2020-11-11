import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Backup extends Thread{

    @Override
    public void run() {

        while(true){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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



            if (i > 2) {
                 dir.listFiles()[0].delete();
            }

            String path = "./backup/backup" + ultimoBackup + ".txt";

            try{
                Files.copy(Paths.get("./hora.txt"), Paths.get(path), REPLACE_EXISTING);
            }catch (IOException ignored){}

            System.out.println("Backup: backup feito!");
        }
    }
}
