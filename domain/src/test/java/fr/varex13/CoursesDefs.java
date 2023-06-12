package fr.varex13;

import java.util.List;
import java.util.Map;

import fr.varex13.port.CourseRepository;
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
        return new Course(entry.get("id"), entry.get("label"));
    }

}
