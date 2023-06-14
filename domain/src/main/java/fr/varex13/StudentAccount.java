package fr.varex13;

import java.math.BigInteger;

public class StudentAccount {
    private final Student student;
    private BigInteger balance;

    public StudentAccount(final Student student, final BigInteger balance) {
        this.student = student;
        this.balance = balance;
    }

    public Student getStudent() {
        return student;
    }

    public void charge(final BigInteger duration) {
        if (balance.compareTo(duration) < 0) {
            throw new SoldeInsuffisantRuntimeExeption();
        }
        balance = balance.subtract(duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentAccount that = (StudentAccount) o;

        if (!student.equals(that.student)) return false;
        return balance.equals(that.balance);
    }

    @Override
    public int hashCode() {
        int result = student.hashCode();
        result = 31 * result + balance.hashCode();
        return result;
    }
}
