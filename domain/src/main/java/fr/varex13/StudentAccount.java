package fr.varex13;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigInteger.ZERO;

public class StudentAccount {
    private final Student student;
    private final List<BigInteger> credits = new ArrayList<>();
    private final List<BigInteger> debits = new ArrayList<>();

    public StudentAccount(final Student student, final BigInteger balance) {
        this.student = student;
        addCredit(balance);
    }

    public Student getStudent() {
        return student;
    }

    public void addCredit(final BigInteger credit) {
        credits.add(credit);
    }

    public void addDebit(final BigInteger credit) {
        debits.add(credit);
    }

    public BigInteger totalCredits() {
        return credits.stream().reduce(ZERO, BigInteger::add);
    }

    public BigInteger totalDebits() {
        return debits.stream().reduce(ZERO, BigInteger::add);
    }

    public BigInteger getBalance() {
        return totalCredits().subtract(totalDebits());
    }

    public void charge(final BigInteger duration) {
        if (getBalance().compareTo(duration) < 0) {
            throw new SoldeInsuffisantRuntimeExeption();
        }
        addDebit(duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentAccount that = (StudentAccount) o;

        if (!student.equals(that.student)) return false;
        if (!credits.equals(that.credits)) return false;
        return debits.equals(that.debits);
    }

    @Override
    public int hashCode() {
        int result = student.hashCode();
        result = 31 * result + credits.hashCode();
        result = 31 * result + debits.hashCode();
        return result;
    }
}
