package fr.varex13.outputport;

import java.util.LinkedHashSet;
import java.util.Set;

import fr.varex13.BookingCourse;

public class BookingCourseRepositoryInMemory implements BookingCourseRepository {
    private final Set<BookingCourse> bookingCourses = new LinkedHashSet<>();

    @Override
    public Set<BookingCourse> all() {
        return bookingCourses;
    }

    @Override
    public void add(BookingCourse bookingCourse) {
        bookingCourses.add(bookingCourse);
    }

}
