package fr.varex13.inputport;

import fr.varex13.*;
import fr.varex13.outputport.BookingCourseRepository;
import fr.varex13.outputport.BookingWorkshopRepository;
import fr.varex13.outputport.StudentAccountRepository;

import static fr.varex13.BookingCourse.bookingBuilder;

public class BookServiceImpl implements BookService {
    private final StudentAccountRepository studentAccountRepository;
    private final BookingCourseRepository bookingCourseRepository;
    private final BookingWorkshopRepository bookingWorkshopRepository;
    private final AuthenticationGateway authenticationGateway;

    public BookServiceImpl(final StudentAccountRepository studentAccountRepository, final BookingCourseRepository bookingCourseRepository, final BookingWorkshopRepository bookingWorkshopRepository, final AuthenticationGateway authenticationGateway) {
        this.studentAccountRepository = studentAccountRepository;
        this.bookingCourseRepository = bookingCourseRepository;
        this.bookingWorkshopRepository = bookingWorkshopRepository;
        this.authenticationGateway = authenticationGateway;
    }

    @Override
    public void handleCourse(final Course course, final Integer duration) {
        final Student student = getStudent();
        chargeCustomer(student, duration);
        applyBooking(bookingBuilder().student(student).course(course).duration(duration).build());
    }

    @Override
    public void handleWorkshop(Workshop workshop) {
        final Student student = getStudent();
        chargeCustomer(student, workshop.getDuration());
        applyBooking(BookingWorkshop.bookingWorkshopBuilder().student(student).workshop(workshop).build());

    }

    private void applyBooking(final BookingCourse bookingCourse) {
        bookingCourseRepository.add(bookingCourse);
    }

    private void applyBooking(BookingWorkshop bookingWorkshop) {
        bookingWorkshopRepository.add(bookingWorkshop);
    }

    private void chargeCustomer(final Student student, final Integer duration) {
        studentAccountRepository.removeDebit(student, duration);
    }

    private Student getStudent() {
        return authenticationGateway.currentStudent().orElseThrow(AuthentificationRuntimeException::new);
    }
}
