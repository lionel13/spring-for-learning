package fr.varex13.student;

import fr.varex13.student.outputport.StudentRepository;

import java.util.Set;

import static fr.varex13.student.StudentEntityMapper.studentToStudentEntity;

public class StudentRepositoryPostgreSql implements StudentRepository {

    private final ZazaRepository zazaRepository;

    public StudentRepositoryPostgreSql(ZazaRepository zazaRepository) {
        this.zazaRepository = zazaRepository;
    }

    @Override
    public void add(Student student) {
        zazaRepository.save(studentToStudentEntity(student));
    }

    @Override
    public Set<Student> all() {
        return null;
    }
}
