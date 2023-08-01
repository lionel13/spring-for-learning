package fr.varex13.outputport;

import fr.varex13.booking.BookingWorkshop;

import java.util.LinkedHashSet;
import java.util.Set;

public class BookingWorkshopRepositoryInMemory implements BookingWorkshopRepository {
    private final Set<BookingWorkshop> bookingCourses = new LinkedHashSet<>();

    @Override
    public Set<BookingWorkshop> all() {
        return bookingCourses;
    }

    @Override
    public void add(BookingWorkshop bookingWorkshop) {
        bookingCourses.add(bookingWorkshop);
    }
}
