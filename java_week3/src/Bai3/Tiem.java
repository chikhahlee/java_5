package Bai3;

class Tiem {
    private int TTiem = 0;
    private int Xuong = 0;
    private final int MAX_STORE_CAPACITY = 10;

    public synchronized void Xuong(int amount) {
        Xuong = amount;
        System.out.println("Xuong da san xuat " + Xuong + " banh. Tong trong lo: " + Xuong);
        transferBunsToStore();
    }

    private synchronized void transferBunsToStore() {
        while (TTiem < MAX_STORE_CAPACITY && Xuong > 0) {
            TTiem++;
            Xuong--;
            System.out.println("Lo banh chyen 1 banh den cua hang. Cua hang hien co: " + TTiem);
        }
    }

    public synchronized boolean buyBun() {
        if (TTiem == 0 && Xuong > 0) {
            transferBunsToStore();
        }

        if (TTiem > 0) {
            TTiem--;
            System.out.println("1 khach hang da mua 1 banh. Cua hang con: " + TTiem);
            return true;
        } else {
            System.out.println("Khong co banh nao cho khach.");
            return false;
        }
    }

    public int getTotalBunsAvailable() {
        return TTiem + Xuong;
    }
}

