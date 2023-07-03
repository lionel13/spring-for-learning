package fr.varex13.port;

import java.util.LinkedHashSet;
import java.util.Set;

import fr.varex13.Course;

public class CourseRepositoryInMemory implements CourseRepository {

    private final Set<Course> courses = new LinkedHashSet<>();

    @Override
    public void add(final Course course) {
        courses.add(course);
    }

    @Override
    public Set<Course> all() {
        return courses;
    }
}
