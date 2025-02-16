package it.unimol.app;

import lombok.Generated;

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

    @Generated
    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    @Generated
    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    @Generated
    public String getVeterinaryName() {
        return veterinaryName;
    }

    @Generated
    public void setVeterinaryName(String veterinaryName) {
        this.veterinaryName = veterinaryName;
    }

    @Generated
    public String getDiagnosis() {
        return diagnosis;
    }

    @Generated
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Generated
    public String getPrescriptions() {
        return prescriptions;
    }

    @Generated
    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    @Generated
    public Float getCost() {
        return cost;
    }

    @Generated
    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Override
    @Generated
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
