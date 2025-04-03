package Bai3;
import java.util.Random;
public class Bai3 {
    public static void main(String[] args) {
        Random random = new Random();
        Tiem bakery = new Tiem();

        BakerThread baker = new BakerThread(bakery);
        baker.start();

        try {
            baker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        int khachHang = random.nextInt(20) + 1;
        System.out.println("Khach hang den mua banh: "+khachHang);

        KhachHang[] customers = new KhachHang[khachHang];
        int customersServed = 0;

        for (int i = 0; i < khachHang; i++) {
            customers[i] = new KhachHang(bakery);
            customers[i].start();
        }

        for (int i = 0; i < khachHang; i++) {
            try {
                customers[i].join();
                if (customers[i].isServed()) {
                    customersServed++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int totalBunsLeft = bakery.getTotalBunsAvailable();

        System.out.println("\nTổng:");
        System.out.println("Số khách hàng được phục vụ: " + customersServed);
        System.out.println("Số khách hàng không mua được bánh: " + (khachHang - customersServed));
        System.out.println("Số bánh còn lại: " + totalBunsLeft);
    }
}

