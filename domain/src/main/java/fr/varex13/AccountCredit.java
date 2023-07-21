package fr.varex13;

import java.time.LocalDateTime;
import java.util.Objects;

public final class AccountCredit {

    private final Student student;
    private final Integer quantity;
    private final LocalDateTime creationDate;

    private AccountCredit(final AccountCreditBuilder accountCreditBuilder) {
        this.quantity = accountCreditBuilder.quantity;
        this.creationDate = accountCreditBuilder.creationDate;
        this.student = accountCreditBuilder.student;
    }

    public static AccountCreditBuilder accountCreditsBuilder() {
        return new AccountCreditBuilder();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Student getStudent() {
        return student;
    }

    public static final class AccountCreditBuilder {
        private Student student;
        private Integer quantity;
        private LocalDateTime creationDate;

        public AccountCreditBuilder student(final Student student) {
            this.student = student;
            return this;
        }

        public AccountCreditBuilder quantity(final Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public AccountCreditBuilder creationDate(final LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public AccountCredit build() {
            if (Objects.isNull(student)) {
                throw new IllegalArgumentException("student ne doit pas être null");
            }
            if (Objects.isNull(quantity)) {
                throw new IllegalArgumentException("quantity ne doit pas être null");
            }
            if (Objects.isNull(creationDate)) {
                throw new IllegalArgumentException("creationDate ne doit pas être null");
            }
            return new AccountCredit(this);
        }
    }

}
