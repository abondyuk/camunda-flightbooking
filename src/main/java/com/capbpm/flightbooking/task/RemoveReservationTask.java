package com.capbpm.flightbooking.task;

import com.capbpm.flightbooking.repository.ReservationRepository;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
public class RemoveReservationTask implements JavaDelegate {
    @Inject
    private ReservationRepository repository;

    @Override
    @SuppressWarnings("unchecked")
    public void execute(DelegateExecution delegateExecution) {
        Map<String, Object> reservation = (Map<String, Object>) delegateExecution.getVariable("value");

        if (reservation.get("reservationId") != null) {
            repository.deleteById((Long) reservation.get("reservationId"));
        }
    }
}