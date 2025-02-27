package src.application.models;

import java.time.LocalDate;

public class Examination {
    private final int examinationId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final boolean stopTest;
    private final String term;

    public Examination(int examinationId, LocalDate startDate, LocalDate endDate, boolean stopTest, String term) {
        this.examinationId = examinationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.stopTest = stopTest;
        this.term = term;
    }

    public String getTerm() {
        return term;
    }
}
