package it.unimol.app;

import it.unimol.app.enumerations.ContractStatus;

import java.io.Serializable;
import java.time.LocalDate;

public class Adoption implements Serializable {
    private static final long serialVersionUID = 1L;

    private int adopter_id;
    private int animal_id;
    private LocalDate adopted_on;
    private ContractStatus contractStatus;

    public Adoption(int adopter_id, int animal_id, LocalDate adopted_on, ContractStatus contractStatus) {
        this.adopter_id = adopter_id;
        this.animal_id = animal_id;
        this.adopted_on = adopted_on;
        this.contractStatus = contractStatus;
    }

    public int getAdopter_id() {
        return adopter_id;
    }

    public void setAdopter_id(int adopter_id) {
        this.adopter_id = adopter_id;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public void setAnimal_id(int animal_id) {
        this.animal_id = animal_id;
    }

    public LocalDate getAdopted_on() {
        return adopted_on;
    }

    public void setAdopted_on(LocalDate adopted_on) {
        this.adopted_on = adopted_on;
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
                "adopter_id=" + adopter_id +
                ", animal_id=" + animal_id +
                ", adopted_on=" + adopted_on +
                ", contractStatus=" + contractStatus +
                '}';
    }
}
