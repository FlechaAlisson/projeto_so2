import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Teclado extends Thread{
    @Override
    public void run() {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        while (true){
          try {
            if (br.readLine() == "guilherme_assanhado") {
                System.out.println("Teclado: usuário digitou algo.");
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
    }
}
