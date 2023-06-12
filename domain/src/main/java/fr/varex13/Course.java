package fr.varex13;

public class Course {
    private final String id;
    private final String label;

    public Course(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
