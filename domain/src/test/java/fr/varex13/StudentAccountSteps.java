package fr.varex13;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.math.BigInteger;

import fr.varex13.port.AuthenticationGateway;
import fr.varex13.port.StudentAccountRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentAccountSteps {

    @Autowired
    private AuthenticationGateway authenticationGateway;
    @Autowired
    private StudentAccountRepository studentAccountRepository;

    @Before
    public void before() {
        studentAccountRepository.all().clear();
    }

    @And("le solde de mon compte est de {biginteger} heures de cours")
    public void leSoldeDeMonCompteEstDeCours(final BigInteger balance) {
        authenticationGateway.currentStudent().ifPresent(student -> {
            final StudentAccount expectedStudentAccount = new StudentAccount(student, balance);
            if (shouldInitStudentAccount(studentAccountRepository)) {
                studentAccountRepository.add(expectedStudentAccount);
            } else {
                assertThat(studentAccountRepository.byId(student.getId()).get(), is(expectedStudentAccount));
            }
        });
    }

    private boolean shouldInitStudentAccount(final StudentAccountRepository studentAccountRepository) {
        return studentAccountRepository.all().isEmpty();
    }
}
