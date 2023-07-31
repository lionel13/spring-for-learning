package fr.varex13;

import static java.util.Objects.isNull;

public final class BookingWorkshop {
    private final Student student;
    private final Workshop workshop;

    private BookingWorkshop(final BookingWorkshopBuilder bookingWorkshopBuilder) {
        this.student = bookingWorkshopBuilder.student;
        this.workshop = bookingWorkshopBuilder.workshop;
    }

    public static BookingWorkshopBuilder bookingWorkshopBuilder() {
        return new BookingWorkshopBuilder();
    }

    public Student getStudent() {
        return student;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public static class BookingWorkshopBuilder {
        private Student student;
        private Workshop workshop;

        public BookingWorkshopBuilder student(final Student student) {
            this.student = student;
            return this;
        }

        public BookingWorkshopBuilder workshop(final Workshop workshop) {
            this.workshop = workshop;
            return this;
        }

        public BookingWorkshop build() {
            if (isNull(student)) {
                throw new IllegalArgumentException("student ne doit pas être null");
            }
            if (isNull(workshop)) {
                throw new IllegalArgumentException("workshop ne doit pas être null");
            }
            return new BookingWorkshop(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingWorkshop bookingCourse = (BookingWorkshop) o;

        if (!student.equals(bookingCourse.student)) return false;
        return workshop.equals(bookingCourse.workshop);
    }

    @Override
    public int hashCode() {
        int result = student.hashCode();
        result = 31 * result + workshop.hashCode();
        return result;
    }
}