package fr.varex13.outputport;

import java.util.LinkedHashSet;
import java.util.Set;

import fr.varex13.prestation.Course;
import fr.varex13.prestation.outputport.CourseRepository;

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
