package fr.varex13.outputport;

import fr.varex13.AccountDebit;
import fr.varex13.Student;

import java.util.Set;

public interface AccountDebitRepository {

    void add(AccountDebit accountDebit);

    Set<AccountDebit> all();

    Set<AccountDebit> byStudent(Student student);
}
