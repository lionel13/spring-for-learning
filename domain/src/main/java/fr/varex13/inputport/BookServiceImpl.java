package fr.varex13.inputport;

import fr.varex13.Booking;
import fr.varex13.Course;
import fr.varex13.Student;
import fr.varex13.outputport.BookingRepository;
import fr.varex13.outputport.StudentAccountRepository;

import static fr.varex13.Booking.bookingBuilder;

public class BookServiceImpl implements BookService {
    private final StudentAccountRepository studentAccountRepository;
    private final BookingRepository bookingRepository;
    private final AuthenticationGateway authenticationGateway;

    public BookServiceImpl(final StudentAccountRepository studentAccountRepository,
                           final BookingRepository bookingRepository,
                           final AuthenticationGateway authenticationGateway) {
        this.studentAccountRepository = studentAccountRepository;
        this.bookingRepository = bookingRepository;
        this.authenticationGateway = authenticationGateway;
    }

    public void handle(final Course course, final Integer duration) {
        final Student student = authenticationGateway.currentStudent().orElseThrow(AuthentificationRuntimeException::new);
        chargeCustomer(student, duration);
        applyBooking(bookingBuilder().student(student).course(course).duration(duration).build());
    }

    private void applyBooking(final Booking booking) {
        bookingRepository.add(booking);
    }

    private void chargeCustomer(final Student student, final Integer duration) {
        studentAccountRepository.removeDebit(student, duration);
    }
}
