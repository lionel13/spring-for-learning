package fr.varex13.outputport;

import fr.varex13.BookingWorkshop;

import java.util.Set;

public interface BookingWorkshopRepository {
    Set<BookingWorkshop> all();

    void add(BookingWorkshop bookingWorkshop);
}
