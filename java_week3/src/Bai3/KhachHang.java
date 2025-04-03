package Bai3;

class KhachHang extends Thread {
    private Tiem bakery;
    private boolean xacNhan = false;

    public KhachHang(Tiem bakery) {
        this.bakery = bakery;
    }

    @Override
    public void run() {
        xacNhan = bakery.buyBun();
    }

    public boolean isServed() {
        return xacNhan;
    }
}