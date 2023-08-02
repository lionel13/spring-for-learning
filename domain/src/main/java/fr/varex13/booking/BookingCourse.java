package fr.varex13.booking;

import fr.varex13.prestation.Course;
import fr.varex13.student.Student;

import static java.util.Objects.isNull;

public final class BookingCourse implements Booking {

    private final Student student;
    private final Course prestation;
    private final Integer duration;

    private BookingCourse(final BookingCourseBuilder bookingCourseBuilder) {
        this.student = bookingCourseBuilder.student;
        this.prestation = bookingCourseBuilder.course;
        this.duration = bookingCourseBuilder.duration;
    }

    public static BookingCourseBuilder bookingBuilder() {
        return new BookingCourseBuilder();
    }


    public Student getStudent() {
        return student;
    }

    public Course getPrestation() {
        return prestation;
    }

    public Integer getDuration() {
        return duration;
    }

    public static class BookingCourseBuilder {
        private Student student;
        private Course course;
        private Integer duration;

        public BookingCourseBuilder student(final Student student) {
            this.student = student;
            return this;
        }

        public BookingCourseBuilder course(final Course course) {
            this.course = course;
            return this;
        }

        public BookingCourseBuilder duration(final Integer duration) {
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
        if (!prestation.equals(bookingCourse.prestation)) return false;
        return duration.equals(bookingCourse.duration);
    }

    @Override
    public int hashCode() {
        int result = student.hashCode();
        result = 31 * result + prestation.hashCode();
        result = 31 * result + duration.hashCode();
        return result;
    }
}