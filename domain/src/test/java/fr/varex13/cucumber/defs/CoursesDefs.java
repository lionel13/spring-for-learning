package fr.varex13.cucumber.defs;

import static fr.varex13.Course.courseBuilder;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import fr.varex13.Course;
import fr.varex13.outputport.CourseRepository;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class CoursesDefs {

    @Autowired
    private CourseRepository courseRepository;

    @Before
    public void before() {
        courseRepository.all().clear();
    }

    @Given("des cours existent:")
    public void desCoursExistent(final List<Course> courses) {
        courses.forEach(courseRepository::add);
    }

    @DataTableType
    public Course courseEntry(final Map<String, String> entry) {
        return courseBuilder().id(UUID.fromString(entry.get("id"))).label(entry.get("label")).build();
    }

}
