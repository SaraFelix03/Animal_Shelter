package it.unimol.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Adopter implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String surname;
    private String cdf;
    private Address address;
    private String telephon;
    private String email;
    private List<Animal> adoptedAnimals;


    public Adopter(String name, String surname, String cdf, Address address,
                   String telephone, String email) {
        this.id = AdoptionManager.getNewAdopterID();
        this.name = name;
        this.surname = surname;
        this.cdf = cdf;
        this.address = address;
        this.telephon = telephone;
        this.email = email;
        this.adoptedAnimals = new ArrayList<Animal>(10);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCdf() {
        return cdf;
    }

    public void setCdf(String cdf) {
        this.cdf = cdf;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getTelephon() {
        return telephon;
    }

    public void setTelephon(String telephon) {
        this.telephon = telephon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addNewAdoptedAnimal(Animal animal){
        adoptedAnimals.add(animal);
    }

    @Override
    public String toString() {
        return "Adopter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", cdf='" + cdf + '\'' +
                ", indirizzo=" + address.toString()+
                ", telephon='" + telephon + '\'' +
                ", email='" + email + '\'' +
                ", adoptedAnimals=" + adoptedAnimals +
                '}';
    }
}
