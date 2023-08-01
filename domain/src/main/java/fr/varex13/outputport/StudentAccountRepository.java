package fr.varex13.outputport;

import fr.varex13.SoldeInsuffisantRuntimeExeption;
import fr.varex13.student.Student;
import fr.varex13.student.StudentAccount;

import java.util.Optional;

public interface StudentAccountRepository {

    Optional<StudentAccount> byStudent(Student student);

    void addCredit(Student student, Integer credit);

    void removeDebit(Student student, Integer debit) throws SoldeInsuffisantRuntimeExeption;
}
