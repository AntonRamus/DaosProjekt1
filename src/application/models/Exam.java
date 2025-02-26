package src.application.models;

public class Exam {
    private final int examId;
    private final String examName;
    private final String term;
    private final String examType;

    public Exam(int examId, String examName, String term, String examType) {
        this.examId = examId;
        this.examName = examName;
        this.term = term;
        this.examType = examType;
    }
}
