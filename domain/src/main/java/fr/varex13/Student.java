package fr.varex13;

import java.util.UUID;

public class Student {
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
            if (id == null) {
                throw new IllegalArgumentException("id ne doit pas être null");
            }
            if (firstName == null) {
                throw new IllegalArgumentException("firstName ne doit pas être null");
            }
            if (lastName == null) {
                throw new IllegalArgumentException("lastName ne doit pas être null");
            }
            return new Student(this);
        }
    }
}
