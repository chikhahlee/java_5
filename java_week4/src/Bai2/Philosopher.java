package Bai2;
public class Philosopher implements Runnable {
    private final Object leftChopstick;
    private final Object rightChopstick;

    public Philosopher(Object leftChopstick, Object rightChopstick) {
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep((int) (Math.random() * 100));
    }

    @Override
    public void run() {
        try {
            // Suy nghĩ
            doAction("Suy nghĩ");
            synchronized (leftChopstick) {
                doAction("Nhặt đũa bên trái");
                synchronized (rightChopstick) {
                    // Ăn
                    doAction("Nhặt đũa bên phải - Ăn");
                    doAction("Đặt đũa bên phải xuống");
                }
                doAction("Đặt đũa bên trái xuống");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
