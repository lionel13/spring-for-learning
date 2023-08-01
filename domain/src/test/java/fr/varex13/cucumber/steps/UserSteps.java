package fr.varex13.cucumber.steps;

import fr.varex13.student.Student;
import fr.varex13.student.inputport.AuthenticationGateway;
import fr.varex13.outputport.StudentRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserSteps {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AuthenticationGateway authenticationGateway;

    @Before
    public void before() {
        authenticationGateway.authenticate(null);
    }

    @Given("je suis authentifié en tant que {string}")
    public void jeSuisAuthentifieEnTantQue(final String userFirstName) {
        final Optional<Student> optionalStudent = studentRepository.all().stream().filter(c -> c.getFirstName().equals(userFirstName)).findFirst();
        optionalStudent.ifPresent(authenticationGateway::authenticate);
        assertThat(authenticationGateway.currentStudent().isPresent(), is(true));
    }

    @Given("je ne suis pas authentifié")
    public void jeNeSuisPasAuthentifié() {
        assertThat(authenticationGateway.currentStudent().isPresent(), is(false));
    }
}
