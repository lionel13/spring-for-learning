package fr.varex13.outputport;

import fr.varex13.AccountCredit;
import fr.varex13.Student;

import java.util.Set;

public interface AccountCreditRepository {

    void add(AccountCredit booking);

    Set<AccountCredit> all();

    Set<AccountCredit> byStudent(Student student);
}
