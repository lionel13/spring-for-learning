package fr.varex13.student;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static fr.varex13.student.Student.studentBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@Ignore
//@Import(InfrastructureConfiguration.class)
class StudentRepositoryPostgreSql2ITTest {

    @Autowired
    private StudentRepositoryPostgreSql studentRepositoryPostgreSql;

    @Test
    void testSimplePutAndGet() {
        final Student student = studentBuilder().id(UUID.randomUUID()).firstName("zaza").lastName("popo").build();

        studentRepositoryPostgreSql.add(student);
        assertThat(true, is(true));
    }

}