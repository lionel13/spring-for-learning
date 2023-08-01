package fr.varex13.outputport;

import fr.varex13.student.AccountCredit;
import fr.varex13.student.Student;

import java.util.Set;

public interface AccountCreditRepository {

    void add(AccountCredit accountCredit);

    Set<AccountCredit> all();

    Set<AccountCredit> byStudent(Student student);
}
