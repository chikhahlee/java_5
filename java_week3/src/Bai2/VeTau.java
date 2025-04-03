package Bai2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class VeTau {
    private final List<Integer> availableSeats;

    public VeTau(int seatCount) {
        availableSeats = new ArrayList<>();
        for (int i = 1; i <= seatCount; i++) {
            availableSeats.add(i);
        }
        Collections.shuffle(availableSeats);
    }

    public synchronized Integer sellTicket(String agentName) {
        if (availableSeats.isEmpty()) {
            return null;
        }
        int seat = availableSeats.remove(0);
        System.out.println(agentName + " bán vé ghế số: " + seat);
        return seat;
    }
}