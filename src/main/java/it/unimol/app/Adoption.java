package it.unimol.app;

import it.unimol.app.enumerations.ContractStatus;

import java.io.Serializable;
import java.time.LocalDate;

public class Adoption implements Serializable {
    private static final long serialVersionUID = 1L;

    private int adopterId;
    private int animalId;
    private LocalDate adoptedOn;
    private ContractStatus contractStatus;

    public Adoption(int adopterId, int animalId, LocalDate adoptedOn, ContractStatus contractStatus) {
        this.adopterId = adopterId;
        this.animalId = animalId;
        this.adoptedOn = adoptedOn;
        this.contractStatus = contractStatus;
    }

    public int getAdopterId() {
        return adopterId;
    }

    public void setAdopterId(int adopterId) {
        this.adopterId = adopterId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public LocalDate getAdoptedOn() {
        return adoptedOn;
    }

    public void setAdoptedOn(LocalDate adoptedOn) {
        this.adoptedOn = adoptedOn;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    @Override
    public String toString() {
        return "Adoption{" +
                "adopter_id=" + adopterId +
                ", animal_id=" + animalId +
                ", adopted_on=" + adoptedOn +
                ", contractStatus=" + contractStatus +
                '}';
    }
}
