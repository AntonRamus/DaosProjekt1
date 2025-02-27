package src.application.models;

//Gruppe 2 - Anton, Sidse og Victor

public class Course {
    private final int courseId;
    private final String courseName;
    private final int numberOfLessons;

    public Course(int courseId, String courseName, int numberOfLessons) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.numberOfLessons = numberOfLessons;
    }

}
