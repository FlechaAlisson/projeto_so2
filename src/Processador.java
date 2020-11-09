public class Processador extends Thread {

    @Override
    public void run() {
        while (true){
            System.out.println("Processador: executei uma instrução.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
