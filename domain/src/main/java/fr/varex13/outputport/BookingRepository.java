package fr.varex13.outputport;

import java.util.Set;

import fr.varex13.Booking;

public interface BookingRepository {
    Set<Booking> all();

    void add(Booking booking);
}
