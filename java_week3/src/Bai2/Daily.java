package Bai2;

import java.util.Random;

class Daily extends Thread {
    private final VeTau veTau;
    private final String daiLy;
    private final Random random = new Random();

    public Daily(VeTau veTau, String daiLy) {
        this.veTau = veTau;
        this.daiLy = daiLy;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(500));
                Integer seat = veTau.sellTicket(daiLy);
                if (seat == null) {
                    System.out.println(daiLy + " thông báo: Hết vé!");
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}