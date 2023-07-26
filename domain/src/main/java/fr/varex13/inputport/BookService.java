package fr.varex13.inputport;

import fr.varex13.Course;

public interface BookService {

    void handle(Course course, Integer duration);
}
