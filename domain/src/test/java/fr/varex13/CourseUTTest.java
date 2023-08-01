package fr.varex13;

import fr.varex13.prestation.Course;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static fr.varex13.prestation.Course.courseBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseUTTest {

    @Test
    void QuandOnCreeUnCourseAlorsOnDoitRetrouverCesValeurs() {
        final Course course = courseBuilder()
                .id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d"))
                .label("libellé cours")
                .build();

        assertThat(course, notNullValue());
        assertThat(course.getId(), is(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d")));
        assertThat(course.getLabel(), is("libellé cours"));
        assertThat(course.toString(), is("Course{" + "id=" + UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d") + ", label='libellé cours'}"));

    }

    @Test
    void QuandDeuxCoursesontLesMemeValeursAlorsIlsSontEgaux() {
        final Course course1 = courseBuilder()
                .id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d"))
                .label("libellé cours")
                .build();
        final Course course2 = courseBuilder()
                .id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d"))
                .label("libellé cours")
                .build();

        assertThat(course1, notNullValue());
        assertThat(course2, notNullValue());
        assertThat(course1, equalTo(course2));
    }

    @Nested
    class Uuid {
        @Test
        void QuandOnCreeUnCourseAvecUnIdVideAlorsOnLeveUneException() {
            final Executable nom = () -> courseBuilder()
                    .label("libellé cours")
                    .build();
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, nom);

            assertThat(illegalArgumentException, notNullValue());
            assertThat(illegalArgumentException.getMessage(), is("id ne doit pas être null"));
        }

        @Test
        void QuandOnCreeUnCourseAvecUnIdNullAlorsOnLeveUneException() {
            final Executable nom = () -> courseBuilder()
                    .id(null)
                    .label("libellé cours")
                    .build();
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, nom);

            assertThat(illegalArgumentException, notNullValue());
            assertThat(illegalArgumentException.getMessage(), is("id ne doit pas être null"));
        }
    }

    @Nested
    class Label {
        @Test
        void QuandOnCreeUnCourseAvecUnLabelVideAlorsOnLeveUneException() {
            final Executable nom = () -> courseBuilder()
                    .id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d"))
                    .build();
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, nom);

            assertThat(illegalArgumentException, notNullValue());
            assertThat(illegalArgumentException.getMessage(), is("label ne doit pas être null"));
        }

        @Test
        void QuandOnCreeUnCourseAvecUnLabelNullAlorsOnLeveUneException() {
            final Executable nom = () -> courseBuilder()
                    .id(UUID.fromString("4fc3f66c-8e76-4b53-9889-c78256836b0d"))
                    .label(null)
                    .build();
            final IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, nom);

            assertThat(illegalArgumentException, notNullValue());
            assertThat(illegalArgumentException.getMessage(), is("label ne doit pas être null"));
        }
    }
}