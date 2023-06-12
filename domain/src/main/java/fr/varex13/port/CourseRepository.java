package fr.varex13.port;

import java.util.Set;

import fr.varex13.Course;

public interface CourseRepository {
    void add(Course course);

    Set<Course> all();
}
