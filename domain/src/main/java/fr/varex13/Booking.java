package fr.varex13;

import static java.util.Objects.isNull;

import java.math.BigInteger;

public final class Booking {
    private final Student student;
    private final Course course;
    private final BigInteger duration;

    private Booking(final BookingBuilder bookingBuilder) {
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

    public BigInteger getDuration() {
        return duration;
    }

    public static class BookingBuilder {
        private Student student;
        private Course course;
        private BigInteger duration;

        public BookingBuilder student(final Student student) {
            this.student = student;
            return this;
        }

        public BookingBuilder course(final Course course) {
            this.course = course;
            return this;
        }

        public BookingBuilder duration(final BigInteger duration) {
            this.duration = duration;
            return this;
        }

        public Booking build() {
            if (isNull(student)) {
                throw new IllegalArgumentException("student ne doit pas être null");
            }
            if (isNull(course)) {
                throw new IllegalArgumentException("course ne doit pas être null");
            }
            if (isNull(duration)) {
                throw new IllegalArgumentException("duration ne doit pas être null");
            }
            return new Booking(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (!student.equals(booking.student)) return false;
        if (!course.equals(booking.course)) return false;
        return duration.equals(booking.duration);
    }

    @Override
    public int hashCode() {
        int result = student.hashCode();
        result = 31 * result + course.hashCode();
        result = 31 * result + duration.hashCode();
        return result;
    }
}