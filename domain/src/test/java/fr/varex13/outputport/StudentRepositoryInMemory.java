package fr.varex13.outputport;

import fr.varex13.student.Student;

import java.util.LinkedHashSet;
import java.util.Set;

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
