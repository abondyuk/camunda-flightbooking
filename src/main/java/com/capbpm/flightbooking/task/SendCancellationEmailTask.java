package com.capbpm.flightbooking.task;

import com.capbpm.flightbooking.EmailSender;
import com.capbpm.flightbooking.ReservationUtility;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
public class SendCancellationEmailTask implements JavaDelegate {
    @Inject
    EmailSender emailSender;

    @Inject
    ReservationUtility reservationUtility;

    @Override
    @SuppressWarnings("unchecked")
    public void execute(DelegateExecution delegateExecution) {
        Map<String, Object> reservation = (Map<String, Object>) delegateExecution.getVariable("value");

        if (reservation.get("reservationId") != null) {
            emailSender.sendEmail(
                    reservation.get("firstName") + " " + reservation.get("lastName"),
                    (String) reservation.get("email"),
                    "Your flight booking has been cancelled",
                    "Dear " + reservation.get("firstName") + " " + reservation.get("lastName")
                            + ". Your flight booking from " + reservation.get("countryFrom")
                            + " to " + reservation.get("countryTo")
                            + " has been cancelled due to the approval time expiration."
            );
        }
    }
}