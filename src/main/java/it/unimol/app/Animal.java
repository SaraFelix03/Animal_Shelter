package it.unimol.app;

import it.unimol.app.enumerations.AdoptionStatus;
import it.unimol.app.enumerations.HealthStatus;
import it.unimol.app.managers.AnimalsManager;
import lombok.Generated;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


/**
 * Represents an animal in the shelter.
 * Stores information such as species, age, health status, and adoption status.
 *
 * Implements {@link Serializable} to allow object serialization.
 *
 * @author Sara F.C.
 * @version 1.0
 */
public class Animal implements Serializable {
    private static final long serialVersionUID = 1L;

    private int ID = AnimalsManager.getNewID();
    private String name;
    private String species;
    private String age;
    private LocalDate admissionDate;
    private HealthStatus healthStatus;
    private AdoptionStatus adoptionStatus;
    private String furtherInformation;

    /**
     * Constructs an {@code Animal} object.
     *
     * @param name              The animal's name.
     * @param species           The species of the animal.
     * @param age               The animal's age.
     * @param admissionDate     The date the animal was admitted to the shelter.
     * @param healthStatus      The current health status of the animal.
     * @param adoptionStatus    The adoption status of the animal.
     * @param furtherInformation Any additional information about the animal.
     */
    public Animal(String name, String species, String age, LocalDate admissionDate, HealthStatus healthStatus, AdoptionStatus adoptionStatus, String furtherInformation) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.admissionDate = admissionDate;
        this.healthStatus = healthStatus;
        this.adoptionStatus = adoptionStatus;
        this.furtherInformation = furtherInformation;
    }

    @Generated
    public int getID() {
        return this.ID;
    }

    @Generated
    public String getName() {
        return this.name;
    }

    @Generated
    public void setName(String name) {
        this.name = name;
    }

    @Generated
    public String getSpecies() {
        return this.species;
    }

    @Generated
    public void setSpecies(String species) {
        this.species = species;
    }

    @Generated
    public String getAge() {
        return this.age;
    }

    @Generated
    public void setAge(String age) {
        this.age = age;
    }

    @Generated
    public LocalDate getAdmissionDate() {
        return this.admissionDate;
    }

    @Generated
    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    @Generated
    public HealthStatus getHealthStatus() {
        return this.healthStatus;
    }

    @Generated
    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    @Generated
    public AdoptionStatus getAdoptionStatus() {
        return this.adoptionStatus;
    }

    @Generated
    public void setAdoptionStatus(AdoptionStatus adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    @Generated
    public String getFurtherInformation() {
        return this.furtherInformation;
    }

    @Generated
    public void setFurtherInformation(String furtherInformation) {
        this.furtherInformation = furtherInformation;
    }

    @Override
    @Generated
    public String toString() {
        return "Animal{ID='" + this.ID + "', name='" + this.name + "', species='" + this.species + "', age=" + this.age + ", admissionDate=" + this.admissionDate + ", healthStatus=" + this.healthStatus + ", adoptionStatus=" + this.adoptionStatus + ", furtherInformation='" + this.furtherInformation + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Animal animal)) {
            return false;
        }
        return Objects.equals(getName(), animal.getName())
                && Objects.equals(getSpecies(), animal.getSpecies()) && Objects.equals(getAge(), animal.getAge())
                && Objects.equals(getAdmissionDate(), animal.getAdmissionDate())
                && getHealthStatus() == animal.getHealthStatus() && getAdoptionStatus() == animal.getAdoptionStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSpecies(), getAge(), getAdmissionDate(), getHealthStatus(),
                getAdoptionStatus());
    }
}
