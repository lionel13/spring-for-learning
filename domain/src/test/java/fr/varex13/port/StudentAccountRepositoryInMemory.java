package fr.varex13.port;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import fr.varex13.StudentAccount;

public class StudentAccountRepositoryInMemory implements StudentAccountRepository {


    private final Set<StudentAccount> studentAccounts = new LinkedHashSet<>();

    @Override
    public void add(StudentAccount studentAccount) {
        studentAccounts.add(studentAccount);
    }

    @Override
    public Optional<StudentAccount> byId(final UUID id) {
        return studentAccounts.stream().filter(c -> c.getStudent().getId().equals(id)).findFirst();
    }

    @Override
    public Set<StudentAccount> all() {
        return studentAccounts;
    }
}
