package it.unimol.app.enumerations;

public enum HealthStatus {
    EXCELLENT("Excellent health condition"),
    GOOD("Good health condition"),
    CRITICAL("Needs urgent care");

    private String description;

    private HealthStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
