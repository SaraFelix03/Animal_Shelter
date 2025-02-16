package it.unimol.app;

import java.io.Serializable;
import java.time.LocalDate;


/**
 * Represents a veterinary visit for an animal.
 * Stores visit details such as date, diagnosis, and cost.
 *
 * Implements {@link Serializable} to allow object serialization.
 *
 * @author Sara F.C.
 * @version 1.0
 */
public class VeterinaryVisit implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDate dateOfVisit;
    private String veterinaryName;
    private String diagnosis;
    private String prescriptions;
    private Float cost;

    /**
     * Constructs a {@code VeterinaryVisit} object.
     *
     * @param dateOfVisit   The date of the visit.
     * @param veterinaryName The name of the veterinarian.
     * @param diagnosis     The diagnosis given during the visit.
     * @param prescriptions The prescribed medications or treatments.
     * @param cost          The cost of the visit.
     */
    public VeterinaryVisit(LocalDate dateOfVisit, String veterinaryName,
                           String diagnosis, String prescriptions, Float cost) {
        this.dateOfVisit = dateOfVisit;
        this.veterinaryName = veterinaryName;
        this.diagnosis = diagnosis;
        this.prescriptions = prescriptions;
        this.cost = cost;
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getVeterinaryName() {
        return veterinaryName;
    }

    public void setVeterinaryName(String veterinaryName) {
        this.veterinaryName = veterinaryName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "VeterinaryVisit{" +
                "dateOfVisit=" + dateOfVisit +
                ", veterinaryName='" + veterinaryName + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", prescriptions='" + prescriptions + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
