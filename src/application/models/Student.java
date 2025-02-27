package src.application.models;

//Gruppe 2 - Anton, Sidse og Victor

public class Student {
    private final int studentId;
    private String studentName;
    private boolean active;

    public Student(int studentId, String studentName, boolean active) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.active = active;
    }

    public String toString() {
        return studentName;
    }
}
