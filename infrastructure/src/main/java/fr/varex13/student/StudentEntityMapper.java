package fr.varex13.student;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentEntityMapper {

    private StudentEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static StudentEntity studentToStudentEntity(final Student student) {
        final StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUuid(student.getId());
        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());
        return studentEntity;
    }

    public static Student studentEntityToStudent(final StudentEntity studentEntity) {
        return Student.studentBuilder()
                .id(studentEntity.getUuid())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .build();
    }

    public static Set<Student> studentEntityToStudent(final List<StudentEntity> studentEntities) {
        return studentEntities.stream().map(StudentEntityMapper::studentEntityToStudent).collect(Collectors.toSet());
    }
}
