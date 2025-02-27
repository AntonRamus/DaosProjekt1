package src.application.models;

//Gruppe 2 - Anton, Sidse og Victor

public class Education {
    private final int educationId;
    private final String educationName;

    public Education(int educationId, String educationName) {
        this.educationId = educationId;
        this.educationName = educationName;
    }

    public int getEducationId() {
        return educationId;
    }

    public String toString() {
        return educationName;
    }
}
