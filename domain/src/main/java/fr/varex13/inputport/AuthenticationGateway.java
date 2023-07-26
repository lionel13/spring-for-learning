package fr.varex13.inputport;

import java.util.Optional;

import fr.varex13.Student;

public interface AuthenticationGateway {

    void authenticate(Student student);

    Optional<Student> currentStudent();

}
