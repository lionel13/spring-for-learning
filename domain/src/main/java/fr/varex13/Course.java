package fr.varex13;

import java.util.Objects;
import java.util.UUID;

public final class Course {
    private final UUID id;
    private final String label;

    private Course(final CourseBuilder courseBuilder) {
        this.id = courseBuilder.id;
        this.label = courseBuilder.label;
    }

    public static CourseBuilder courseBuilder() {
        return new CourseBuilder();
    }

    public UUID getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static final class CourseBuilder {
        private UUID id;
        private String label;

        public CourseBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        public CourseBuilder label(final String label) {
            this.label = label;
            return this;
        }

        public Course build() {
            if (Objects.isNull(id)) {
                throw new IllegalArgumentException("id ne doit pas être null");
            }
            if (Objects.isNull(label)) {
                throw new IllegalArgumentException("label ne doit pas être null");
            }
            return new Course(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (!id.equals(course.id)) return false;
        return label.equals(course.label);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + label.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }
}
