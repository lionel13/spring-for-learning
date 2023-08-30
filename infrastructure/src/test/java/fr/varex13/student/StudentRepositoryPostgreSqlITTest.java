package fr.varex13.student;

import fr.varex13.AbstractInfrastructureTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class StudentRepositoryPostgreSqlITTest extends AbstractInfrastructureTest {

    @Autowired
    private StudentRepositoryPostgreSql studentRepositoryPostgreSql;

    @Test
    void testSimplePutAndGet() {
        final UUID id = UUID.randomUUID();
        final String firstName = "zaza";
        final String lastName = "popo";
        studentRepositoryPostgreSql.add(Student.studentBuilder().id(id).firstName(firstName).lastName(lastName).build());
        Set<Student> students = studentRepositoryPostgreSql.all();

        Optional<Student> first = students.stream().findFirst();
        assertThat(first.isPresent(), is(true));
        Student student = first.get();
        assertThat(student.getId(), is(id));
        assertThat(student.getFirstName(), is(firstName));
        assertThat(student.getLastName(), is(lastName));

    }

}