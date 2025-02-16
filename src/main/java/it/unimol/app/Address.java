package it.unimol.app;

import lombok.Generated;

import java.io.Serializable;

/**
 * Represents an address associated with an adopter.
 * This class is used to store address details such as street, city, and postal code.
 *
 * Implements {@link Serializable} to allow object serialization.
 *
 * @author Sara F.C
 * @version 1.0
 */
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    private String street;
    private String streetNumber;
    private String city;
    private String postalCode;
    private String country;

    /**
     * Constructs an {@code Address} object.
     *
     * @param street       The street name.
     * @param streetNumber The street number.
     * @param city         The city name.
     * @param postalCode   The postal code.
     * @param country      The country name.
     */
    public Address(String street, String streetNumber, String city, String postalCode, String country) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    @Generated
    public String getStreet() {
        return street;
    }

    @Generated
    public void setStreet(String street) {
        this.street = street;
    }

    @Generated
    public String getStreetNumber() {
        return streetNumber;
    }

    @Generated
    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    @Generated
    public String getCity() {
        return city;
    }

    @Generated
    public void setCity(String city) {
        this.city = city;
    }

    @Generated
    public String getPostalCode() {
        return postalCode;
    }

    @Generated
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Generated
    public String getCountry() {
        return country;
    }

    @Generated
    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    @Generated
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
