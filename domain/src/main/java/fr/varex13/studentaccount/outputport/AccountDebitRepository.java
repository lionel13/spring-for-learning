package fr.varex13.studentaccount.outputport;

import fr.varex13.studentaccount.AccountDebit;
import fr.varex13.student.Student;

import java.util.Set;

public interface AccountDebitRepository {

    void add(AccountDebit accountDebit);

    Set<AccountDebit> all();

    Set<AccountDebit> byStudent(Student student);
}
