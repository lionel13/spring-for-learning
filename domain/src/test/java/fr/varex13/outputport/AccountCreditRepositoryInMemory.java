package fr.varex13.outputport;

import fr.varex13.student.AccountCredit;
import fr.varex13.student.Student;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountCreditRepositoryInMemory implements AccountCreditRepository {

    private final Set<AccountCredit> accountCredits = new LinkedHashSet<>();

    @Override
    public void add(final AccountCredit accountCredit) {
        accountCredits.add(accountCredit);
    }

    @Override
    public Set<AccountCredit> all() {
        return accountCredits;
    }

    @Override
    public Set<AccountCredit> byStudent(final Student student) {
        return accountCredits.stream().filter(c -> c.getStudent().equals(student)).collect(Collectors.toSet());
    }
}
