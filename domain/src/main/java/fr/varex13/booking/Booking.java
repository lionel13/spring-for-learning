package fr.varex13.booking;

import fr.varex13.prestation.Prestation;
import fr.varex13.student.Student;

public interface Booking {

    Student getStudent();

    Prestation getPrestation();

    Integer getDuration();
}
