package fr.varex13.inputport;

import fr.varex13.Course;
import fr.varex13.Workshop;

public interface BookService {

    void handleCourse(Course course, Integer duration);

    void handleWorkshop(Workshop workshop);
}
