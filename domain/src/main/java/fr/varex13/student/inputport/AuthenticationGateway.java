package fr.varex13.student.inputport;

import java.util.Optional;

import fr.varex13.student.Student;

public interface AuthenticationGateway {

    void authenticate(Student student);

    Optional<Student> currentStudent();

}
