package com.kk.flight.database;

import com.kk.flight.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    Optional<Flight> findByFlightNumberAndDepartureDate(Long flightNumber,String departureDate);
    List<Flight> findAllByDepartureAirportIATACodeAndDepartureDate(String iata, String date);
    List<Flight> findAllByArrivalAirportIATACodeAndDepartureDate(String iata, String date);



}
