package com.capbpm.flightbooking;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ReservationUtility {
    public String getMaskedReservationId(String value) {
        String outputValue = "";
        if (value != null && !value.isEmpty()) {
            outputValue = Base64.getEncoder().encodeToString(value.getBytes());
        }

        return outputValue;
    }

    public String[] getReservationIdByMaskedValue(String value) {
        String[] stringReservationList = {};
        if (value != null && !value.isEmpty()) {
            byte[] decodedBytes = Base64.getDecoder().decode(value);
            String stringReservationId = new String(decodedBytes);

            stringReservationList = stringReservationId.split(",");
        }

        return stringReservationList;
    }
}
