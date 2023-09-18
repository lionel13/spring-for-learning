package fr.varex13.student.outputport;

import fr.varex13.student.Student;

import java.util.Set;

public interface StudentRepository {
    void add(Student student);

    Set<Student> all();
}
