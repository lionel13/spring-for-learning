package fr.varex13.configuration;

import fr.varex13.port.AuthenticationGateway;
import fr.varex13.port.AuthenticationGatewayInMemory;
import fr.varex13.port.BookingRepository;
import fr.varex13.port.BookingRepositoryInMemory;
import fr.varex13.port.CourseRepository;
import fr.varex13.port.CourseRepositoryInMemory;
import fr.varex13.port.StudentAccountRepository;
import fr.varex13.port.StudentAccountRepositoryInMemory;
import fr.varex13.port.StudentRepository;
import fr.varex13.port.StudentRepositoryInMemory;
import org.springframework.context.annotation.Bean;

public class TestConfig {

    @Bean
    public StudentRepository studentRepository() {
        return new StudentRepositoryInMemory();
    }

    @Bean
    public CourseRepository courseRepository() {
        return new CourseRepositoryInMemory();
    }

    @Bean
    public StudentAccountRepository studentAccountRepository() {
        return new StudentAccountRepositoryInMemory();
    }

    @Bean
    public BookingRepository bookingRepository() {
        return new BookingRepositoryInMemory();
    }

    @Bean
    public AuthenticationGateway authenticationGateway() {
        return new AuthenticationGatewayInMemory();
    }
}
