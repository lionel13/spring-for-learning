package fr.varex13;

import java.math.BigDecimal;
import java.util.Objects;

public class StudentAccount {
    private final String id;
    private BigDecimal balance;

    public StudentAccount(final String id, final BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void charge(final String duration) {
        final BigDecimal subtract = balance.subtract(BigDecimal.valueOf(Long.parseLong(duration)));
        if (subtract.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Solde insuffisant");
        }
        balance = subtract;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentAccount that = (StudentAccount) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        return result;
    }
}
