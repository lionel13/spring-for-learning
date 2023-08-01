package fr.varex13.booking;

import fr.varex13.outputport.BookingCourseRepository;
import fr.varex13.outputport.BookingWorkshopRepository;
import fr.varex13.prestation.Course;
import fr.varex13.prestation.Prestation;
import fr.varex13.prestation.PrestationType;
import fr.varex13.prestation.Workshop;
import fr.varex13.student.Student;

import java.util.Map;

import static fr.varex13.booking.BookingCourse.bookingBuilder;
import static fr.varex13.prestation.PrestationType.COURSE;
import static fr.varex13.prestation.PrestationType.WORKSHOP;

public class BookingContext {

    private final Map<PrestationType, BookingStrategy> strategies;

    public BookingContext(final BookingWorkshopRepository bookingWorkshopRepository, final BookingCourseRepository bookingCourseRepository) {
        strategies = Map.of(COURSE, getBookingCourseStrategy(bookingCourseRepository), WORKSHOP, getBookingWorkshopStrategy(bookingWorkshopRepository));
    }

    private static BookingStrategy getBookingCourseStrategy(final BookingCourseRepository bookingCourseRepository) {
        return (student, prestation, duration) -> bookingCourseRepository.add(bookingBuilder().student(student).course((Course) prestation).duration(duration).build());
    }

    private static BookingStrategy getBookingWorkshopStrategy(final BookingWorkshopRepository bookingWorkshopRepository) {
        return (student, prestation, duration) -> bookingWorkshopRepository.add(BookingWorkshop.bookingWorkshopBuilder().student(student).workshop((Workshop) prestation).build());
    }

    public void handleBooking(final Student student, final Prestation prestation, final Integer duration) {
        strategies.get(prestation.getPrestationType()).applyBooking(student, prestation, duration);
    }
}
