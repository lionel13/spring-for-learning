package fr.varex13.port;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import fr.varex13.StudentAccount;

public interface StudentAccountRepository {
    void add(StudentAccount expectedStudentAccount);

    Optional<StudentAccount> byId(UUID id);

    Set<StudentAccount> all();

}
