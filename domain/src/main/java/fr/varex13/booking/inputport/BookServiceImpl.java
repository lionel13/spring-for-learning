package fr.varex13.booking.inputport;

import fr.varex13.booking.BookingContext;
import fr.varex13.outputport.StudentAccountRepository;
import fr.varex13.prestation.Prestation;
import fr.varex13.student.Student;
import fr.varex13.student.inputport.AuthenticationGateway;
import fr.varex13.student.inputport.AuthentificationRuntimeException;

public class BookServiceImpl implements BookService {
    private final StudentAccountRepository studentAccountRepository;
    private final AuthenticationGateway authenticationGateway;
    private final BookingContext bookingContext;

    public BookServiceImpl(final StudentAccountRepository studentAccountRepository,
                           final AuthenticationGateway authenticationGateway,
                           final BookingContext bookingContext) {
        this.studentAccountRepository = studentAccountRepository;
        this.bookingContext = bookingContext;
        this.authenticationGateway = authenticationGateway;
    }

    @Override
    public void createBooking(final Prestation prestation, final Integer duration) {
        final Student student = getStudent();
        chargeCustomer(student, duration);
        bookingContext.handleBooking(student, prestation, duration);
    }

    private void chargeCustomer(final Student student, final Integer duration) {
        studentAccountRepository.removeDebit(student, duration);
    }

    private Student getStudent() {
        return authenticationGateway.currentStudent().orElseThrow(AuthentificationRuntimeException::new);
    }
}
