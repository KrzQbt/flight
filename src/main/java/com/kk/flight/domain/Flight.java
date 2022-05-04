package com.kk.flight.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Flight {
    @Id
    Long flightId;
    Long flightNumber;
    String departureAirportIATACode;
    String arrivalAirportIATACode;
    String departureDate;
    String timeWithZone;

}
