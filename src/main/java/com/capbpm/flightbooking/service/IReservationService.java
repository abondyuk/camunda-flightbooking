package com.capbpm.flightbooking.service;

import com.capbpm.flightbooking.model.Reservation;

import java.util.List;

public interface IReservationService {

    List<Reservation> getAllReservations();
}