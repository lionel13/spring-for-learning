package fr.varex13.outputport;

import fr.varex13.AccountDebit;
import fr.varex13.Student;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountDebitRepositoryInMemory implements AccountDebitRepository {

    private final Set<AccountDebit> accountDebits = new LinkedHashSet<>();

    @Override
    public void add(final AccountDebit accountCredit) {
        accountDebits.add(accountCredit);
    }

    @Override
    public Set<AccountDebit> all() {
        return accountDebits;
    }

    @Override
    public Set<AccountDebit> byStudent(Student student) {
        return accountDebits.stream().filter(c -> c.getStudent().equals(student)).collect(Collectors.toSet());
    }
}
