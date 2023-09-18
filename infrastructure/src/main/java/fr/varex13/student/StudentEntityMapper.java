package fr.varex13.student;

import java.util.List;

public class StudentEntityMapper {

    private static final Mapper<Student, StudentEntity> studentEntityToStudent = studentEntity -> Student.studentBuilder().id(studentEntity.getUuid()).firstName(studentEntity.getFirstName()).lastName(studentEntity.getLastName()).build();

    private static final Mapper<StudentEntity, Student> studentToStudentEntity = student -> {
        final StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUuid(student.getId());
        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());
        return studentEntity;
    };

    private static final MapperList<Student, StudentEntity> studentEntitiesToStudents = studentEntities -> studentEntities.stream().map(studentEntityToStudent::map).toList();

    private static final MapperList<StudentEntity, Student> studentToStudentEntities = students -> students.stream().map(studentToStudentEntity::map).toList();

    private StudentEntityMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static StudentEntity studentToStudentEntity(final Student student) {
        return studentToStudentEntity.map(student);
    }

    public static Student studentToStudentEntity(final StudentEntity studentEntity) {
        return studentEntityToStudent.map(studentEntity);
    }

    public static List<StudentEntity> studentsToStudentEntities(final List<Student> student) {
        return studentToStudentEntities.map(student);
    }

    public static List<Student> studentEntitiesToStudents(final List<StudentEntity> studentEntities) {
        return studentEntitiesToStudents.map(studentEntities);
    }
}
