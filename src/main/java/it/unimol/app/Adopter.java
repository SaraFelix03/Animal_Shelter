package it.unimol.app;

import it.unimol.app.managers.AdoptionManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an adopter who can adopt animals from the shelter.
 * Stores personal details and a list of adopted animals.
 *
 * Implements {@link Serializable} to allow object serialization.
 *
 * @author Sara F.C.
 * @version 1.0
 */
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


    /**
     * Constructs an {@code Adopter} object and assigns a unique ID.
     *
     * @param name     The adopter's first name.
     * @param surname  The adopter's last name.
     * @param cdf      The adopter's identification code.
     * @param address  The adopter's address.
     * @param telephone The adopter's contact number.
     * @param email    The adopter's email address.
     */
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


    /**
     * Adds a new adopted animal to the adopter's list.
     *
     * @param animal The animal to be added.
     */
    public void addNewAdoptedAnimal(Animal animal) {
        adoptedAnimals.add(animal);
    }

    @Override
    public String toString() {
        return "Adopter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", cdf='" + cdf + '\'' +
                ", indirizzo=" + address.toString() +
                ", telephon='" + telephon + '\'' +
                ", email='" + email + '\'' +
                ", adoptedAnimals=" + adoptedAnimals +
                '}';
    }
}
