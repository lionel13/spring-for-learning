package fr.varex13.booking.inputport;

import fr.varex13.prestation.Prestation;

public interface BookService {
    void createBooking(Prestation course, Integer duration);
}
