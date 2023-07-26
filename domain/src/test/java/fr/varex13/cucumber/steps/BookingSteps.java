package fr.varex13.cucumber.steps;

import fr.varex13.Booking;
import fr.varex13.Course;
import fr.varex13.Student;
import fr.varex13.inputport.AuthenticationGateway;
import fr.varex13.inputport.BookService;
import fr.varex13.outputport.BookingRepository;
import fr.varex13.outputport.CourseRepository;
import fr.varex13.outputport.StudentAccountRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static fr.varex13.Booking.bookingBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BookingSteps {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private StudentAccountRepository studentAccountRepository;
    @Autowired
    private AuthenticationGateway authenticationGateway;
    @Autowired
    private BookService bookService;

    @Before
    public void before() {
        bookingRepository.all().clear();
    }

    BookingAttempt bookingAttempt = new BookingAttempt();

    @When("je tente de réserver le cours {string} pour {int}")
    public void jeTenteDeRéserverLeCours(final String label, final Integer duration) {
        try {

            courseRepository.all().stream().filter(course -> course.getLabel().equals(label)).forEach(course -> {
                try {
                    bookService.handle(course, duration);
                    bookingAttempt.setStudent(authenticationGateway.currentStudent().get());
                    bookingAttempt.setCourse(course);
                    bookingAttempt.setDuration(duration);

                } catch (RuntimeException ex) {
                    bookingAttempt.setExcepceptionMessage(ex.getMessage());
                }
            });
        } catch (RuntimeException ex) {
            bookingAttempt.setExcepceptionMessage(ex.getMessage());

        }
    }

    @Then("la réservation est effective")
    public void laRéservationEstEffective() {
        final Set<Booking> bookings = bookingRepository.all();
        assertThat(bookings.size(), is(1));
        final Booking booking = bookings.iterator().next();
        assertThat(bookingBuilder().student(bookingAttempt.student).course(bookingAttempt.course).duration(bookingAttempt.duration).build(), is(booking));

    }

    @Then("la réservation n'est pas effective")
    public void laRéservationNEstPasEffective() {
        final Set<Booking> bookings = bookingRepository.all();
        assertThat(bookings.size(), is(0));
    }

    @And("et une alerte pour insuffisance de solde se lève")
    public void etUneAlertePourInsuffisanceDeSoldeSeLève() {
        assertThat(bookingAttempt.excepceptionMessage, is("Solde insuffisant"));
    }

    @And("et une alerte pour identification du client impossible se lève")
    public void etUneAlertePourIdentificationDuClientImpossibleSeLève() {
        assertThat(bookingAttempt.excepceptionMessage, is("Non authentifié"));
    }

    private static class BookingAttempt {
        private Student student;
        private Course course;
        private Integer duration;

        private String excepceptionMessage;

        public void setStudent(final Student student) {
            this.student = student;
        }

        public void setCourse(final Course course) {
            this.course = course;
        }

        public void setDuration(final Integer duration) {
            this.duration = duration;
        }

        public void setExcepceptionMessage(String excepceptionMessage) {
            this.excepceptionMessage = excepceptionMessage;
        }
    }
}
