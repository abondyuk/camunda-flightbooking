package com.capbpm.flightbooking.service;

import com.capbpm.flightbooking.model.Reservation;
import com.capbpm.flightbooking.repository.ReservationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements IReservationService {

    @Autowired
    private ReservationRepository repository;

    @Override
    public List<Reservation> getAllReservations() {

        List<Reservation> reservations = (List<Reservation>) repository.findAll();

        return reservations;
    }
}