package fr.varex13;

import java.time.LocalDateTime;
import java.util.Objects;

public final class AccountDebit {

    private final Student student;

    private final Integer quantity;
    private final LocalDateTime creationDate;

    private AccountDebit(final AccountDebitBuilder accountDebitBuilder) {
        this.student = accountDebitBuilder.student;
        this.quantity = accountDebitBuilder.quantity;
        this.creationDate = accountDebitBuilder.creationDate;
    }

    public static AccountDebitBuilder accountDebitBuilder() {
        return new AccountDebitBuilder();
    }

    public Student getStudent() {
        return student;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public static final class AccountDebitBuilder {
        private Student student;
        private Integer quantity;
        private LocalDateTime creationDate;

        public AccountDebitBuilder student(final Student student) {
            this.student = student;
            return this;
        }

        public AccountDebitBuilder quantity(final Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public AccountDebitBuilder creationDate(final LocalDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public AccountDebit build() {
            if (Objects.isNull(student)) {
                throw new IllegalArgumentException("student ne doit pas être null");
            }
            if (Objects.isNull(quantity)) {
                throw new IllegalArgumentException("quantity ne doit pas être null");
            }
            if (Objects.isNull(creationDate)) {
                throw new IllegalArgumentException("creationDate ne doit pas être null");
            }
            return new AccountDebit(this);
        }
    }

}
