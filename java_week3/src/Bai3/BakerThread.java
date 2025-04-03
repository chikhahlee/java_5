package Bai3;

import java.util.Random;
class BakerThread extends Thread {
    private Tiem bakery;

    public BakerThread(Tiem bakery) {
        this.bakery = bakery;
    }

    @Override
    public void run() {
        Random random = new Random();
        int bunsProduced = random.nextInt(14) + 5;
        bakery.Xuong(bunsProduced);
    }
}