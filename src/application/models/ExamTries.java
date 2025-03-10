package src.application.models;

import java.time.LocalDate;

//Gruppe 2 - Anton, Sidse og Victor

public class ExamTries {
    private final int examTryId;
    private final String grade;
    private final LocalDate date;

    public ExamTries(int examTryId, String grade, LocalDate date) {
        this.examTryId = examTryId;
        this.grade = grade;
        this.date = date;
    }
}
