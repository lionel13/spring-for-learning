package fr.varex13;

import static java.util.Objects.isNull;

public final class BookingCourse {
    private final Student student;
    private final Course course;
    private final Integer duration;

    private BookingCourse(final BookingBuilder bookingBuilder) {
        this.student = bookingBuilder.student;
        this.course = bookingBuilder.course;
        this.duration = bookingBuilder.duration;
    }

    public static BookingBuilder bookingBuilder() {
        return new BookingBuilder();
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public Integer getDuration() {
        return duration;
    }

    public static class BookingBuilder {
        private Student student;
        private Course course;
        private Integer duration;

        public BookingBuilder student(final Student student) {
            this.student = student;
            return this;
        }

        public BookingBuilder course(final Course course) {
            this.course = course;
            return this;
        }

        public BookingBuilder duration(final Integer duration) {
            this.duration = duration;
            return this;
        }

        public BookingCourse build() {
            if (isNull(student)) {
                throw new IllegalArgumentException("student ne doit pas être null");
            }
            if (isNull(course)) {
                throw new IllegalArgumentException("course ne doit pas être null");
            }
            if (isNull(duration)) {
                throw new IllegalArgumentException("duration ne doit pas être null");
            }
            return new BookingCourse(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingCourse bookingCourse = (BookingCourse) o;

        if (!student.equals(bookingCourse.student)) return false;
        if (!course.equals(bookingCourse.course)) return false;
        return duration.equals(bookingCourse.duration);
    }

    @Override
    public int hashCode() {
        int result = student.hashCode();
        result = 31 * result + course.hashCode();
        result = 31 * result + duration.hashCode();
        return result;
    }
}