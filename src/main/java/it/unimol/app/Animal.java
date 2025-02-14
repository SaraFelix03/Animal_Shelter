package it.unimol.app;

import it.unimol.app.enumerations.AdoptionStatus;
import it.unimol.app.enumerations.HealthStatus;
import it.unimol.app.managers.AnimalsManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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

    public Animal(String name, String species, String age, LocalDate admissionDate, HealthStatus healthStatus, AdoptionStatus adoptionStatus, String furtherInformation) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.admissionDate = admissionDate;
        this.healthStatus = healthStatus;
        this.adoptionStatus = adoptionStatus;
        this.furtherInformation = furtherInformation;
    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public LocalDate getAdmissionDate() {
        return this.admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public HealthStatus getHealthStatus() {
        return this.healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public AdoptionStatus getAdoptionStatus() {
        return this.adoptionStatus;
    }

    public void setAdoptionStatus(AdoptionStatus adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }

    public String getFurtherInformation() {
        return this.furtherInformation;
    }

    public void setFurtherInformation(String furtherInformation) {
        this.furtherInformation = furtherInformation;
    }

    public String toString() {
        return "Animal{ID='" + this.ID + "', name='" + this.name + "', species='" + this.species + "', age=" + this.age + ", admissionDate=" + this.admissionDate + ", healthStatus=" + this.healthStatus + ", adoptionStatus=" + this.adoptionStatus + ", furtherInformation='" + this.furtherInformation + "'}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Animal)) {
            return false;
        } else {
            Animal animal = (Animal)o;
            return Objects.equals(this.ID, animal.ID) && Objects.equals(this.getSpecies(), animal.getSpecies());
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.ID, this.getSpecies()});
    }
}
