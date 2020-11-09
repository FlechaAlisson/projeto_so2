import java.util.Scanner;

public class Teclado extends Thread{
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true){
            String s = sc.nextLine();
            if (s.isBlank()) {
                System.out.println("Teclado: usuário digitou algo.");
            }
        }
    }
}
