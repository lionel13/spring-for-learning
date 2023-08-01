package fr.varex13.outputport;

import fr.varex13.student.AccountDebit;
import fr.varex13.student.Student;

import java.util.Set;

public interface AccountDebitRepository {

    void add(AccountDebit accountDebit);

    Set<AccountDebit> all();

    Set<AccountDebit> byStudent(Student student);
}
