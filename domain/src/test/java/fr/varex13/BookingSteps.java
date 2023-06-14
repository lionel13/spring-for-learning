package fr.varex13;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Set;

import fr.varex13.port.AuthenticationGateway;
import fr.varex13.port.BookingRepository;
import fr.varex13.port.CourseRepository;
import fr.varex13.port.StudentAccountRepository;
import fr.varex13.usecases.BookCourse;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingSteps {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private StudentAccountRepository studentAccountRepository;
    @Autowired
    private AuthenticationGateway authenticationGateway;

    @Before
    public void before() {
        bookingRepository.all().clear();
    }

    BookingAttempt bookingAttempt = new BookingAttempt();

    @When("je tente de réserver le cours {string} pour {string}")
    public void jeTenteDeRéserverLeCours(final String label, final String duration) {
        try {
            final BookCourse bookCourse = new BookCourse(studentAccountRepository, bookingRepository, authenticationGateway);

            courseRepository.all().stream().filter(course -> course.getLabel().equals(label)).forEach(course -> {
                try {
                    bookCourse.handle(course, duration);
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
        assertThat(new Booking(bookingAttempt.student, bookingAttempt.course, bookingAttempt.duration), is(booking));

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
        private String duration;

        private String excepceptionMessage;

        public void setStudent(final Student student) {
            this.student = student;
        }

        public void setCourse(final Course course) {
            this.course = course;
        }

        public void setDuration(final String duration) {
            this.duration = duration;
        }

        public void setExcepceptionMessage(String excepceptionMessage) {
            this.excepceptionMessage = excepceptionMessage;
        }
    }
}
