package fr.varex13.inputport;

import fr.varex13.Course;

import java.math.BigInteger;

public interface BookService {

    void handle(Course course, BigInteger duration);
}
