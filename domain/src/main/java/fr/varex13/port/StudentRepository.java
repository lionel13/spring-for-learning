package fr.varex13.port;

import java.util.Set;

import fr.varex13.Student;

public interface StudentRepository {
    void add(Student student);

    Set<Student> all();
}
