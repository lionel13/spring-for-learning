package fr.varex13.configuration;

import fr.varex13.booking.inputport.BookService;
import fr.varex13.booking.inputport.BookServiceImpl;
import fr.varex13.booking.BookingContext;
import fr.varex13.inputport.AuthenticationGatewayInMemory;
import fr.varex13.outputport.*;
import fr.varex13.student.inputport.AuthenticationGateway;
import org.springframework.context.annotation.Bean;

public class TestConfig {

    @Bean
    public BookService bookService(final StudentAccountRepository studentAccountRepository, final AuthenticationGateway authenticationGateway, final BookingContext bookingContext) {
        return new BookServiceImpl(studentAccountRepository, authenticationGateway, bookingContext);
    }

    @Bean
    public BookingContext bookStrategy(final BookingWorkshopRepository bookingWorkshopRepository, final BookingCourseRepository bookingCourseRepository) {
        return new BookingContext(bookingWorkshopRepository, bookingCourseRepository);
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
