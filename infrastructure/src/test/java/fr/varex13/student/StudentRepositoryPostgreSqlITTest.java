package fr.varex13.student;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Set;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@Testcontainers
@SpringBootTest
class StudentRepositoryPostgreSqlITTest {

    @Autowired
    private StudentRepositoryPostgreSql studentRepositoryPostgreSql;

    @Container
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1").withDatabaseName("employees").withUsername("sa").withPassword("sa");

    @BeforeAll
    public static void setup() {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }


    @AfterAll
    public static void tearDown() {
        postgreSQLContainer.stop();
    }

    @Test
    void testSimplePutAndGet() {
        studentRepositoryPostgreSql.add(Student.studentBuilder().id(UUID.randomUUID()).firstName("zaza").lastName("popo").build());
        Set<Student> all = studentRepositoryPostgreSql.all();
        assertThat(all, hasSize(1));
    }

}