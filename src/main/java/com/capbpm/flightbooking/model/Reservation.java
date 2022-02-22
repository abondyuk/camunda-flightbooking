package com.capbpm.flightbooking.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private Date travelInDate;
    private String countryFrom;
    private String countryTo;

    public Reservation() {
    }

    public Reservation(Long id, String firstName, String lastName, String email,
                       Date travelInDate, String countryFrom, String countryTo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.travelInDate = travelInDate;
        this.countryFrom = countryFrom;
        this.countryTo = countryTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTravelInDate() {
        return travelInDate;
    }

    public void setTravelInDate(Date travelInDate) {
        this.travelInDate = travelInDate;
    }

    public String getCountryFrom() {
        return countryFrom;
    }

    public void setCountryFrom(String countryFrom) {
        this.countryFrom = countryFrom;
    }

    public String getCountryTo() {
        return countryTo;
    }

    public void setCountryTo(String countryTo) {
        this.countryTo = countryTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Reservation))
            return false;
        Reservation reservation = (Reservation) o;
        return Objects.equals(this.id, reservation.id)
                && Objects.equals(this.email, reservation.email)
                && Objects.equals(this.travelInDate, reservation.travelInDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.email, this.travelInDate);
    }

    @Override
    public String toString() {
        return id + "," + firstName + "," + lastName + "," + email + "," + travelInDate + ","
                + countryFrom + "," + countryTo;
    }
}