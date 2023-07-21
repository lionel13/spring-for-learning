package fr.varex13.outputport;

import fr.varex13.SoldeInsuffisantRuntimeExeption;
import fr.varex13.Student;
import fr.varex13.StudentAccount;

import java.util.Optional;

public interface StudentAccountRepository {

    Optional<StudentAccount> byStudent(Student student);

    void addCredit(Student student, Integer credit);

    void removeDebit(Student student, Integer debit) throws SoldeInsuffisantRuntimeExeption;
}
