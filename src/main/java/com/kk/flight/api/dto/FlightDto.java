package com.kk.flight.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class FlightDto {
    Long flightId;
    Long flightNumber;
    String departureAirportIATACode;
    String arrivalAirportIATACode;
    String departureDate;

}
