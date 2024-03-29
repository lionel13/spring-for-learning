package fr.varex13.cucumber.defs;

import static fr.varex13.Student.studentBuilder;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import fr.varex13.Student;
import fr.varex13.outputport.StudentRepository;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

public class Studentdefs {

    @Autowired
    private StudentRepository studentRepository;

    @Before
    public void before() {
        studentRepository.all().clear();
    }

    @Given("des élèves existent:")
    public void desElevesExistent(final List<Student> students) {
        students.forEach(studentRepository::add);
    }

    @DataTableType
    public Student studentEntry(final Map<String, String> entry) {
        return studentBuilder().id(UUID.fromString(entry.get("id"))).firstName(entry.get("firstName")).lastName(entry.get("lastName")).build();
    }
}
