package Bai1;

public class sochan extends Thread {
    @Override
    public void run() {
        int n = 10;
        for (int i = 0; i <= n; i++) {
            if (i % 2 == 0) {
                System.out.println("So chan: " + i);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
