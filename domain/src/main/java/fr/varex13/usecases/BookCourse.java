package fr.varex13.usecases;

import static fr.varex13.Booking.bookingBuilder;

import java.math.BigInteger;

import fr.varex13.Booking;
import fr.varex13.Course;
import fr.varex13.Student;
import fr.varex13.port.AuthenticationGateway;
import fr.varex13.port.BookingRepository;
import fr.varex13.port.StudentAccountRepository;

public class BookCourse {
    private final StudentAccountRepository studentAccountRepository;
    private final BookingRepository bookingRepository;
    private final AuthenticationGateway authenticationGateway;

    public BookCourse(final StudentAccountRepository studentAccountRepository, final BookingRepository bookingRepository, final AuthenticationGateway authenticationGateway) {
        this.studentAccountRepository = studentAccountRepository;
        this.bookingRepository = bookingRepository;
        this.authenticationGateway = authenticationGateway;
    }

    public void handle(final Course course, final BigInteger duration) {
        final Student student = authenticationGateway.currentStudent().orElseThrow(AuthentificationRuntimeException::new);
        chargeCustomer(student, duration);
        applyBooking(bookingBuilder().student(student).course(course).duration(duration).build());
    }

    private void applyBooking(final Booking booking) {
        bookingRepository.add(booking);
    }

    private void chargeCustomer(final Student student, final BigInteger duration) {
        studentAccountRepository.byId(student.getId()).ifPresent(studentAccount -> studentAccount.charge(duration));
    }
}
