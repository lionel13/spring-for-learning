package fr.varex13.inputport;

import fr.varex13.student.Student;
import fr.varex13.student.inputport.AuthenticationGateway;

import java.util.Optional;

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
