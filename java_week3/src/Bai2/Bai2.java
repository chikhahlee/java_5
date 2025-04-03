package Bai2;

import java.util.Random;

public class Bai2 {
    public static void main(String[] args) {
        Random random = new Random();
        int seatCount = random.nextInt(10) + 1;
        System.out.println("Toa tàu có tổng cộng " + seatCount + " ghế.");

        VeTau trainTicket = new VeTau(seatCount);

        Daily daily1 = new Daily(trainTicket, "Đại lý 1");
        Daily daily2 = new Daily(trainTicket, "Đại lý 2");
        Daily daily3 = new Daily(trainTicket, "Đại lý 3");

        daily1.start();
        daily2.start();
        daily3.start();

        try {
            daily1.join();
            daily2.join();
            daily3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Tất cả vé đã được bán hết!");
    }
}
