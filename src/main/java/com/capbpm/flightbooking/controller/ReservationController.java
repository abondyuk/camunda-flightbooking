package com.capbpm.flightbooking.controller;

import com.capbpm.flightbooking.model.Reservation;
import com.capbpm.flightbooking.repository.ReservationRepository;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/reservation")
public class ReservationController
{
    @Inject
    private RuntimeService runtimeService;

    @Inject
    private ReservationRepository repository;

    @PostMapping("/submit")
    HashMap<String, String> newReservation(@RequestBody Reservation newReservation) {
        Reservation reservation = repository.save(newReservation);

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("reservationId", reservation.getId());
        variables.put("firstName", reservation.getFirstName());
        variables.put("lastName", reservation.getLastName());
        variables.put("email", reservation.getEmail());
        variables.put("reservationStringValue", reservation.toString());

        runtimeService.createMessageCorrelation("reservationSendEvent")
                .setVariable("value", variables).correlate();

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Flight booking reservation has been created successfully");
        response.put("reservationId", String.valueOf(reservation.getId()));

        return response;
    }

    @GetMapping("/confirm")
    @ResponseBody
    HashMap<String, String> confirmReservation(@RequestParam String id) {
        runtimeService.createMessageCorrelation("confirmationApproved")
                .setVariable("value", id).correlate();

        HashMap<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Flight booking has been approved");

        return response;
    }
}