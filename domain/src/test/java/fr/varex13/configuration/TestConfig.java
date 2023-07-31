package fr.varex13.configuration;

import fr.varex13.inputport.AuthenticationGateway;
import fr.varex13.inputport.AuthenticationGatewayInMemory;
import fr.varex13.inputport.BookService;
import fr.varex13.inputport.BookServiceImpl;
import fr.varex13.outputport.*;
import org.springframework.context.annotation.Bean;

public class TestConfig {

    @Bean
    public BookService bookService(final StudentAccountRepository studentAccountRepository, final BookingCourseRepository bookingCourseRepository, final BookingWorkshopRepository bookingWorkshopRepository, final AuthenticationGateway authenticationGateway) {
        return new BookServiceImpl(studentAccountRepository, bookingCourseRepository, bookingWorkshopRepository, authenticationGateway);
    }

    @Bean
    public StudentRepository studentRepository() {
        return new StudentRepositoryInMemory();
    }

    @Bean
    public CourseRepository courseRepository() {
        return new CourseRepositoryInMemory();
    }

    @Bean
    public WorkshopRepository workshopRepository() {
        return new WorkshopRepositoryInMemory();
    }

    @Bean
    public BookingCourseRepository bookingCourseRepository() {
        return new BookingCourseRepositoryInMemory();
    }

    @Bean
    public BookingWorkshopRepository bookingWorkshopRepository() {
        return new BookingWorkshopRepositoryInMemory();
    }

    @Bean
    public AccountCreditRepository accountCreditRepository() {
        return new AccountCreditRepositoryInMemory();
    }

    @Bean
    public AccountDebitRepository accountDebitRepository() {
        return new AccountDebitRepositoryInMemory();
    }

    @Bean
    public StudentAccountRepository studentAccountRepository(final AccountCreditRepository accountCreditRepository, final AccountDebitRepository accountDebitRepository) {
        return new StudentAccountRepositoryInMemory(accountCreditRepository, accountDebitRepository);
    }

    @Bean
    public AuthenticationGateway authenticationGateway() {
        return new AuthenticationGatewayInMemory();
    }
}
