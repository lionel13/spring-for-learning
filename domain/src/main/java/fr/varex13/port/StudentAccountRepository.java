package fr.varex13.port;

import java.util.Optional;
import java.util.Set;

import fr.varex13.StudentAccount;

public interface StudentAccountRepository {
    void add(StudentAccount expectedStudentAccount);

    Optional<StudentAccount> byId(String id);

    Set<StudentAccount> all();

}
