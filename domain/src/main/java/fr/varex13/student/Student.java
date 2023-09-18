package fr.varex13.student;

import static java.util.Objects.isNull;

import java.util.UUID;

public final class Student {
    private final UUID id;
    private final String firstName;
    private final String lastName;

    private Student(final StudentBuilder studentBuilder) {
        this.id = studentBuilder.id;
        this.firstName = studentBuilder.firstName;
        this.lastName = studentBuilder.lastName;
    }

    public static StudentBuilder studentBuilder() {
        return new StudentBuilder();
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static class StudentBuilder {
        private UUID id;
        private String firstName;
        private String lastName;

        public StudentBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        public StudentBuilder firstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public StudentBuilder lastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Student build() {
            if (isNull(id)) {
                throw new IllegalArgumentException("id ne doit pas être null");
            }
            if (isNull(firstName)) {
                throw new IllegalArgumentException("firstName ne doit pas être null");
            }
            if (isNull(lastName)) {
                throw new IllegalArgumentException("lastName ne doit pas être null");
            }
            if (firstName.length() < 1) {
                throw new IllegalArgumentException("firstName doit être au moins de longueur 1");
            }
            if (lastName.length() < 1) {
                throw new IllegalArgumentException("lastName doit être au moins de longueur 1");
            }
            return new Student(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (!id.equals(student.id)) return false;
        if (!firstName.equals(student.firstName)) return false;
        return lastName.equals(student.lastName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
    }
}
