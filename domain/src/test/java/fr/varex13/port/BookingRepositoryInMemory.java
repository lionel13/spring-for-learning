package fr.varex13.port;

import java.util.LinkedHashSet;
import java.util.Set;

import fr.varex13.Booking;

public class BookingRepositoryInMemory implements BookingRepository {
    private final Set<Booking> bookings = new LinkedHashSet<>();

    @Override
    public Set<Booking> all() {
        return bookings;
    }

    @Override
    public void add(Booking booking) {
        bookings.add(booking);
    }

}
