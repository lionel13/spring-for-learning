package fr.varex13.student;

import java.util.List;

public class StudentEntityMapper {

    private static Mapper<Student, StudentEntity> student = studentEntity -> Student.studentBuilder().id(studentEntity.getUuid()).firstName(studentEntity.getFirstName()).lastName(studentEntity.getLastName()).build();

    private static Mapper<StudentEntity, Student> studentEntity = student -> {
        final StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUuid(student.getId());
        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());
        return studentEntity;
    };

    private static MapperList<Student, StudentEntity> students = studentEntities -> studentEntities.stream().map(studentEntity -> student.map(studentEntity)).toList();

    private static MapperList<StudentEntity, Student> studentEntities = students -> students.stream().map(student -> studentEntity.map(student)).toList();

    private StudentEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static StudentEntity studentToStudentEntity(final Student student) {
        return studentEntity.map(student);
    }

    public static Student studentToStudentEntity(final StudentEntity studentEntity) {
        return student.map(studentEntity);
    }

    public static List<StudentEntity> studentsToStudentEntities(final List<Student> student) {
        return studentEntities.map(student);
    }

    public static List<Student> studentEntitiesToStudents(final List<StudentEntity> studentEntities) {
        return students.map(studentEntities);
    }
}
