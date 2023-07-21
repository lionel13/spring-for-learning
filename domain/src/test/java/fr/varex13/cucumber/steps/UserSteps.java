package fr.varex13.cucumber.steps;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Optional;

import fr.varex13.Student;
import fr.varex13.inputport.AuthenticationGateway;
import fr.varex13.outputport.StudentRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

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
//        final Optional<Student> optionalStudent = studentRepository.all().stream().filter(c -> c.getFirstName().equals(userFirstName)).findFirst();
//        optionalStudent.ifPresent(authenticationGateway::authenticate);
        assertThat(authenticationGateway.currentStudent().isPresent(), is(false));
    }
}
