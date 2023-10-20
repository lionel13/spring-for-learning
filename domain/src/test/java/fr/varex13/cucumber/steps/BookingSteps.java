package fr.varex13.cucumber.steps;

import fr.varex13.booking.BookingCourse;
import fr.varex13.booking.BookingWorkshop;
import fr.varex13.booking.inputport.BookService;
import fr.varex13.booking.outputport.BookingCourseRepository;
import fr.varex13.booking.outputport.BookingWorkshopRepository;
import fr.varex13.prestation.Course;
import fr.varex13.prestation.Workshop;
import fr.varex13.prestation.outputport.CourseRepository;
import fr.varex13.prestation.outputport.WorkshopRepository;
import fr.varex13.student.Student;
import fr.varex13.student.inputport.AuthenticationGateway;
import fr.varex13.studentaccount.outputport.StudentAccountRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static fr.varex13.booking.BookingCourse.bookingBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BookingSteps {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private WorkshopRepository workshopRepository;
    @Autowired
    private BookingCourseRepository bookingCourseRepository;
    @Autowired
    private BookingWorkshopRepository bookingWorkshopRepository;
    @Autowired
    private StudentAccountRepository studentAccountRepository;
    @Autowired
    private AuthenticationGateway authenticationGateway;
    //    @Autowired
//    private BookService bookService;
    @Autowired
    private BookService bookService;

    @Before
    public void before() {
        bookingCourseRepository.all().clear();
        bookingWorkshopRepository.all().clear();
    }

    private final BookingAttemptCourses bookingAttemptCourses = new BookingAttemptCourses();
    private final BookingAttemptWorkshop bookingAttemptWorkshop = new BookingAttemptWorkshop();

    @When("je tente de réserver le cours {string} pour {int}")
    public void jeTenteDeRéserverLeCours(final String label, final Integer duration) {
        try {
            courseRepository.all().stream().filter(course -> course.getLabel().equals(label)).forEach(course -> {
                bookService.createBooking(course, duration);
                bookingAttemptCourses.setStudent(authenticationGateway.currentStudent().get());
                bookingAttemptCourses.setCourse(course);
                bookingAttemptCourses.setDuration(duration);
            });
        } catch (RuntimeException ex) {
            bookingAttemptCourses.setExceptionMessage(ex.getMessage());

        }
    }

    @When("je tente de réserver l'atelier {string}")
    public void jeTenteDeRéserverLAtelier(String workshopLabel) {
        try {
            workshopRepository.all().stream().filter(workshop -> workshop.getLabel().equals(workshopLabel)).forEach(workshop -> {
                bookService.createBooking(workshop, workshop.getDuration());
                bookingAttemptWorkshop.setStudent(authenticationGateway.currentStudent().get());
                bookingAttemptWorkshop.setWorkshop(workshop);
            });
        } catch (RuntimeException ex) {
            bookingAttemptCourses.setExceptionMessage(ex.getMessage());
        }
    }

    @Then("la réservation est effective")
    public void laRéservationEstEffective() {
        final Set<BookingCourse> bookingCourses = bookingCourseRepository.all();
        assertThat(bookingCourses.size(), is(1));
        final BookingCourse bookingCourse = bookingCourses.iterator().next();
        assertThat(bookingBuilder().student(bookingAttemptCourses.getStudent()).course(bookingAttemptCourses.course).duration(bookingAttemptCourses.duration).build(), is(bookingCourse));
    }

    @Then("la réservation de l'atelier est effective")
    public void laRéservationDeLAtelierEstEffective() {
        final Set<BookingWorkshop> bookingWorkshops = bookingWorkshopRepository.all();
        assertThat(bookingWorkshops.size(), is(1));
        final BookingWorkshop bookingWorkshop = bookingWorkshops.iterator().next();
        assertThat(BookingWorkshop.bookingWorkshopBuilder().student(bookingAttemptWorkshop.getStudent()).workshop(bookingAttemptWorkshop.workshop).build(), is(bookingWorkshop));

    }

    @Then("la réservation n'est pas effective")
    public void laRéservationNEstPasEffective() {
        final Set<BookingCourse> bookingCourses = bookingCourseRepository.all();
        assertThat(bookingCourses.size(), is(0));
    }

    @Then("la réservation de l'atelier n est pas effective")
    public void laRéservationDeLAtelierNEstPasEffective() {
        final Set<BookingWorkshop> bookingWorkshops = bookingWorkshopRepository.all();
        assertThat(bookingWorkshops.size(), is(0));
    }

    @And("et une alerte pour insuffisance de solde se lève")
    public void etUneAlertePourInsuffisanceDeSoldeSeLève() {
        assertThat(((BookingAttempt) bookingAttemptCourses).getExceptionMessage(), is("Solde insuffisant"));
    }

    @And("et une alerte pour identification du client impossible se lève")
    public void etUneAlertePourIdentificationDuClientImpossibleSeLève() {
        assertThat(((BookingAttempt) bookingAttemptCourses).getExceptionMessage(), is("Non authentifié"));
    }

    private static class BookingAttempt {
        private Student student;
        private String exceptionMessage;

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public String getExceptionMessage() {
            return exceptionMessage;
        }

        public void setExceptionMessage(String exceptionMessage) {
            this.exceptionMessage = exceptionMessage;
        }
    }

    private static class BookingAttemptCourses extends BookingAttempt {
        private Course course;
        private Integer duration;

        public Course getCourse() {
            return course;
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }
    }

    private static class BookingAttemptWorkshop extends BookingAttempt {
        private Workshop workshop;

        public Workshop getWorkshop() {
            return workshop;
        }

        public void setWorkshop(Workshop workshop) {
            this.workshop = workshop;
        }
    }
}
