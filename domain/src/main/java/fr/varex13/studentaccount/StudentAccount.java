package fr.varex13.studentaccount;

import fr.varex13.student.Student;

import java.util.Objects;

public final class StudentAccount {

    private final Student student;
    private final Integer balance;

    private StudentAccount(final StudentAccountBuilder studentAccountBuilder) {
        this.student = studentAccountBuilder.student;
        this.balance = studentAccountBuilder.balance;
    }

    public static StudentAccountBuilder studentAccountBuilder() {
        return new StudentAccountBuilder();
    }

    public Student getStudent() {
        return student;
    }

    public Integer getBalance() {
        return balance;
    }

    public static final class StudentAccountBuilder {
        private Student student;

        private Integer balance;

        public StudentAccountBuilder student(final Student student) {
            this.student = student;
            return this;
        }

        public StudentAccountBuilder balance(final Integer balance) {
            this.balance = balance;
            return this;
        }

        public StudentAccount build() {
            if (Objects.isNull(student)) {
                throw new IllegalArgumentException("student ne doit pas être null");
            }
            if (Objects.isNull(balance)) {
                throw new IllegalArgumentException("balance ne doit pas être null");
            }
            return new StudentAccount(this);
        }
    }

}
