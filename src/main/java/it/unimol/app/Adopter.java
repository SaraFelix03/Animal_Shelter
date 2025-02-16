package it.unimol.app;

import it.unimol.app.managers.AdoptionManager;
import lombok.Generated;

import java.io.Serializable;

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
    }

    @Generated
    public int getId() {
        return id;
    }

    @Generated
    public String getName() {
        return name;
    }

    @Generated
    public void setName(String name) {
        this.name = name;
    }

    @Generated
    public String getSurname() {
        return surname;
    }

    @Generated
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Generated
    public String getCdf() {
        return cdf;
    }

    @Generated
    public void setCdf(String cdf) {
        this.cdf = cdf;
    }

    @Generated
    public Address getAddress() {
        return address;
    }

    @Generated
    public void setAddress(Address address) {
        this.address = address;
    }

    @Generated
    public String getTelephon() {
        return telephon;
    }

    @Generated
    public void setTelephon(String telephon) {
        this.telephon = telephon;
    }

    @Generated
    public String getEmail() {
        return email;
    }

    @Generated
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    @Generated
    public String toString() {
        return "Adopter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", cdf='" + cdf + '\'' +
                ", indirizzo=" + address.toString() +
                ", telephon='" + telephon + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
