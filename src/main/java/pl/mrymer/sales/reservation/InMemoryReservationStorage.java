package pl.mrymer.sales.reservation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryReservationStorage {
    Map<String, Reservation> reservationMap;

    public InMemoryReservationStorage() {
        this.reservationMap = new HashMap<>();
    }

    public void save(Reservation reservation) {
        reservationMap.put(reservation.getId(), reservation);
    }

    public Optional<Reservation> findById(String reservationId) {
        return Optional.ofNullable(reservationMap.get(reservationId));
    }
}
