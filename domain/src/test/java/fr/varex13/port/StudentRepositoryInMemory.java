package fr.varex13.port;

import java.util.LinkedHashSet;
import java.util.Set;

import fr.varex13.Student;

public class StudentRepositoryInMemory implements StudentRepository {

    private final Set<Student> students = new LinkedHashSet<>();

    @Override
    public void add(final Student student) {
        students.add(student);
    }

    @Override
    public Set<Student> all() {
        return students;
    }
}
