package com.capbpm.flightbooking.task;

import com.capbpm.flightbooking.EmailSender;
import com.capbpm.flightbooking.ReservationUtility;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SendFinalConfirmationEmailTask implements JavaDelegate {
    @Inject
    EmailSender emailSender;

    @Inject
    ReservationUtility reservationUtility;

    @Override
    @SuppressWarnings("unchecked")
    public void execute(DelegateExecution delegateExecution) {
        String confirmationId = (String) delegateExecution.getVariable("value");
        if (confirmationId != null && !confirmationId.isEmpty()) {
            String[] stringReservationList = reservationUtility.getReservationIdByMaskedValue(confirmationId);
            if (stringReservationList.length > 3) {
                emailSender.sendEmail(
                        stringReservationList[1] + " " + stringReservationList[2],
                        stringReservationList[3],
                        "Your flight booking has been confirmed",
                        "Your flight has been approved and confirmed. Enjoy your trip"
                );
            }
        }
    }
}