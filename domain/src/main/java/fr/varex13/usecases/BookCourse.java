package fr.varex13.usecases;

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

    public void handle(final Course course, final String duration) {
        Student student = authenticationGateway.currentStudent().orElseThrow(() -> new RuntimeException("Non authentifié"));
        chargeCustomer(student, duration);
        applyBooking(new Booking(student, course, duration));
    }

    private void applyBooking(final Booking booking) {
        bookingRepository.add(booking);
    }

    private void chargeCustomer(final Student student, final String duration) {
        studentAccountRepository.byId(student.getId()).ifPresent(studentAccount -> studentAccount.charge(duration));
    }
}
