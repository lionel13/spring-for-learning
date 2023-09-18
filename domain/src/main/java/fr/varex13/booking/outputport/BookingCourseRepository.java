package fr.varex13.booking.outputport;

import java.util.Set;

import fr.varex13.booking.BookingCourse;

public interface BookingCourseRepository {
    Set<BookingCourse> all();

    void add(BookingCourse bookingCourse);
}
