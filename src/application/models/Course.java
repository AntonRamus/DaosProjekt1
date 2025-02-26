package src.application.models;

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
