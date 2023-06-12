package fr.varex13.port;

import java.util.Optional;

import fr.varex13.Student;

public class AuthenticationGatewayInMemory implements AuthenticationGateway {
    private Student student;

    @Override
    public void authenticate(final Student student) {
        this.student = student;
    }

    @Override
    public Optional<Student> currentStudent() {
        return Optional.ofNullable(student);
    }
}
