package fr.varex13.student;

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
}
