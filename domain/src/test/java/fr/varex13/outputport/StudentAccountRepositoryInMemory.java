package fr.varex13.outputport;

import fr.varex13.*;
import fr.varex13.studentaccount.AccountCredit;
import fr.varex13.studentaccount.AccountDebit;
import fr.varex13.student.Student;
import fr.varex13.studentaccount.StudentAccount;
import fr.varex13.studentaccount.outputport.AccountCreditRepository;
import fr.varex13.studentaccount.outputport.AccountDebitRepository;
import fr.varex13.studentaccount.outputport.StudentAccountRepository;

import java.util.Optional;
import java.util.Set;

import static fr.varex13.studentaccount.AccountCredit.accountCreditsBuilder;
import static fr.varex13.studentaccount.AccountDebit.accountDebitBuilder;
import static java.time.LocalDateTime.now;

public class StudentAccountRepositoryInMemory implements StudentAccountRepository {

    private final AccountCreditRepository accountCreditRepository;

    private final AccountDebitRepository accountDebitRepository;

    public StudentAccountRepositoryInMemory(final AccountCreditRepository accountCreditRepository,
                                            final AccountDebitRepository accountDebitRepository) {
        this.accountCreditRepository = accountCreditRepository;
        this.accountDebitRepository = accountDebitRepository;
    }

    @Override
    public Optional<StudentAccount> byStudent(Student student) {
        int balance = getBalance(student);
        return Optional.of(StudentAccount.studentAccountBuilder()
                .student(student)
                .balance(balance)
                .build());
    }

    @Override
    public void addCredit(Student student, Integer credit) {
        accountCreditRepository.add(accountCreditsBuilder()
                .student(student)
                .quantity(credit)
                .creationDate(now())
                .build());
    }

    @Override
    public void removeDebit(Student student, Integer debit) throws SoldeInsuffisantRuntimeExeption {
        if (getBalance(student).compareTo(debit) < 0) {
            throw new SoldeInsuffisantRuntimeExeption();
        }
        accountDebitRepository.add(accountDebitBuilder()
                .student(student)
                .quantity(debit)
                .creationDate(now())
                .build());
    }

    private Integer getBalance(Student student) {
        Set<AccountCredit> accountCredits = accountCreditRepository.byStudent(student);
        Integer totalCredits = accountCredits.stream().reduce(0, (subtotal, accountCredit) -> subtotal + accountCredit.getQuantity(), Integer::sum);
        Set<AccountDebit> accountDebits = accountDebitRepository.byStudent(student);
        Integer totalDebits = accountDebits.stream().reduce(0, (subtotal, accountDebit) -> subtotal + accountDebit.getQuantity(), Integer::sum);
        return totalCredits - totalDebits;
    }

}
