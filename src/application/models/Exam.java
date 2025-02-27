package src.application.models;

public class Exam {
    private final int examId;
    private final String examName;
    private final String term;
    private final String examType;
    private final Education education;

    public Exam(int examId, String examName, String term, String examType, Education education) {
        this.examId = examId;
        this.examName = examName;
        this.term = term;
        this.examType = examType;
        this.education = education;

    }

    public int getExamId() {
        return examId;
    }

    public Education getEducation() {
        return education;
    }

    public String toString() {
        return examName;
    }
}
