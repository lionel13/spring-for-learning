package fr.varex13;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static fr.varex13.Student.studentBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentUTTest {

    @Test
    void QuandOnCreeUnStudentAlorsOnDoitRetrouverCesValeurs() {
        final Student student = studentBuilder().id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d")).firstName("myFirstName").lastName("myLastName").build();

        assertThat(student, notNullValue());
        assertThat(student.getId(), is(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d")));
        assertThat(student.getFirstName(), is("myFirstName"));
        assertThat(student.getLastName(), is("myLastName"));
        assertThat(student.toString(), is("Student{" + "id=" + UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d") + ", firstName='myFirstName', lastName='myLastName'}"));

    }

    @Test
    void QuandDeuxStudentsontLesMemeValeursAlorsIlsSontEgaux() {
        final Student student1 = studentBuilder().id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d")).firstName("myFirstName").lastName("myLastName").build();
        final Student student2 = studentBuilder().id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d")).firstName("myFirstName").lastName("myLastName").build();

        assertThat(student1, notNullValue());
        assertThat(student2, notNullValue());
        assertThat(student1, equalTo(student2));
    }

    @Nested
    class Uuid {
        @Test
        void QuandOnCreeUnStudentAvecUnIdVideAlorsOnLeveUneException() {
            final Executable nom = () -> studentBuilder().firstName("myFirstName").lastName("myLastName").build();
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, nom);

            assertThat(illegalArgumentException, notNullValue());
            assertThat(illegalArgumentException.getMessage(), is("id ne doit pas être null"));
        }

        @Test
        void QuandOnCreeUnStudentAvecUnIdNullAlorsOnLeveUneException() {
            final Executable nom = () -> studentBuilder().id(null).firstName("myFirstName").lastName("myLastName").build();
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, nom);

            assertThat(illegalArgumentException, notNullValue());
            assertThat(illegalArgumentException.getMessage(), is("id ne doit pas être null"));
        }
    }

    @Nested
    class FirstName {
        @Test
        void QuandOnCreeUnStudentAvecUnNomVideAlorsOnLeveUneException() {
            final Executable nom = () -> studentBuilder().id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d")).lastName("myLastName").build();
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, nom);

            assertThat(illegalArgumentException, notNullValue());
            assertThat(illegalArgumentException.getMessage(), is("firstName ne doit pas être null"));
        }

        @Test
        void QuandOnCreeUnStudentAvecUnNomNullAlorsOnLeveUneException() {
            final Executable nom = () -> studentBuilder().id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d")).firstName(null).lastName("myLastName").build();
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, nom);

            assertThat(illegalArgumentException, notNullValue());
            assertThat(illegalArgumentException.getMessage(), is("firstName ne doit pas être null"));
        }

        @Test
        void QuandOnCreeUnStudentAvecUnNomDeLongeurZeroAlorsOnLeveUneException() {
            final Executable nom = () -> studentBuilder().id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d")).firstName("").lastName("myLastName").build();
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, nom);

            assertThat(illegalArgumentException, notNullValue());
            assertThat(illegalArgumentException.getMessage(), is("firstName doit être au moins de longueur 1"));
        }
    }

    @Nested
    class LastName {
        @Test
        void QuandOnCreeUnStudentAvecUnLastNameVideAlorsOnLeveUneException() {
            final Executable nom = () -> studentBuilder().id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d")).firstName("myFirstName").build();
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, nom);

            assertThat(illegalArgumentException, notNullValue());
            assertThat(illegalArgumentException.getMessage(), is("lastName ne doit pas être null"));
        }

        @Test
        void QuandOnCreeUnStudentAvecUnLastNameNullAlorsOnLeveUneException() {
            final Executable nom = () -> studentBuilder().id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d")).lastName(null).firstName("myFirstName").build();
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, nom);

            assertThat(illegalArgumentException, notNullValue());
            assertThat(illegalArgumentException.getMessage(), is("lastName ne doit pas être null"));
        }

        @Test
        void QuandOnCreeUnStudentAvecUnLastNameDeLongeurZeroAlorsOnLeveUneException() {
            final Executable nom = () -> studentBuilder().id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d")).lastName("").firstName("myFirstName").build();
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, nom);

            assertThat(illegalArgumentException, notNullValue());
            assertThat(illegalArgumentException.getMessage(), is("lastName doit être au moins de longueur 1"));
        }
    }
}