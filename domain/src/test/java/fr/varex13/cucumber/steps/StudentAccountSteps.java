package fr.varex13.cucumber.steps;

import fr.varex13.inputport.AuthenticationGateway;
import fr.varex13.outputport.AccountCreditRepository;
import fr.varex13.outputport.AccountDebitRepository;
import fr.varex13.outputport.StudentAccountRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class StudentAccountSteps {

    @Autowired
    private AuthenticationGateway authenticationGateway;
    @Autowired
    private StudentAccountRepository studentAccountRepository;
    @Autowired
    private AccountCreditRepository accountCreditRepository;
    @Autowired
    private AccountDebitRepository accountDebitRepository;

    @Before
    public void before() {
        accountCreditRepository.all().clear();
        accountDebitRepository.all().clear();
    }

    @And("le solde initial de mon compte est de {int} heures de cours")
    public void leSoldeInitialDeMonCompteEstDeCours(final Integer balance) {
        authenticationGateway.currentStudent().ifPresent(student -> studentAccountRepository.addCredit(student, balance));
    }

    @And("le solde de mon compte est de {int} heures de cours")
    public void leSoldeDeMonCompteEstDeCours(final Integer balance) {
        authenticationGateway.currentStudent().ifPresent(student -> assertThat(studentAccountRepository.byStudent(student).get().getBalance(), is(balance)));
    }

    @And("le solde initial de mon compte est de {int} heures de cours V2")
    public void leSoldeInitialDeMonCompteEstDeCoursV2(final Integer balance) {
        authenticationGateway.currentStudent().ifPresent(student -> studentAccountRepository.addCredit(student, balance));
    }

    @And("le solde de mon compte est de {int} heures de cours V2")
    public void leSoldeDeMonCompteEstDeCoursV2(final Integer balance) {
        authenticationGateway.currentStudent().ifPresent(student -> assertThat(studentAccountRepository.byStudent(student).get().getBalance(), is(balance)));
    }

    @When("je tente d'ajouter {int} de crédit")
    public void jeTenteDAjouterNb_heuresDeCrédit(final Integer creditToAdd) {

        authenticationGateway.currentStudent().ifPresent(student -> {
            studentAccountRepository.addCredit(student, creditToAdd.intValue());
        });
    }

    private String exceptionMessage;

    @When("je tente d'ajouter {int} de débit")
    public void jeTenteDAjouterNb_heuresDeDébit(final Integer debitToAdd) {
        try {
            authenticationGateway.currentStudent().ifPresent(student -> studentAccountRepository.removeDebit(student, debitToAdd.intValue()));
        } catch (RuntimeException ex) {
            exceptionMessage = ex.getMessage();
        }
    }

    @Then("une alerte pour insuffisance de solde se lève")
    public void uneAlertePourInsuffisanceDeSoldeSeLève() {
        assertThat(exceptionMessage, is("Solde insuffisant"));
    }
}
