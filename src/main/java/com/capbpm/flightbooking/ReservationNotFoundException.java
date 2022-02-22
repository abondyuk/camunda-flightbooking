package com.capbpm.flightbooking;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(Long id) {
        super("Could not find reservation " + id);
    }
}