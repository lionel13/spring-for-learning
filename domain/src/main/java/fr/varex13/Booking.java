package fr.varex13;

import java.util.Objects;

public class Booking {
    private final Student student;
    private final Course course;
    private final String duration;

    public Booking(final Student student, final Course course, final String duration) {
        this.student = student;
        this.course = course;
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (!Objects.equals(student, booking.student)) return false;
        if (!Objects.equals(course, booking.course)) return false;
        return Objects.equals(duration, booking.duration);
    }

    @Override
    public int hashCode() {
        int result = student != null ? student.hashCode() : 0;
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }
}