package fr.varex13;

public class Student {
    private final String id;
    private final String firstName;
    private final String lastName;

    public Student(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
}
