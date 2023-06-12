package fr.varex13.port;

import java.util.Set;

import fr.varex13.Booking;

public interface BookingRepository {
    Set<Booking> all();

    void add(Booking booking);
}
