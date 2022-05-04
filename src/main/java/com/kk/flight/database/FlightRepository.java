package com.kk.flight.database;

import com.kk.flight.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    Optional<Flight> findByFlightNumberAndDepartureDate(Long flightNumber,String departureDate);

    @Query("SELECT COUNT(*) " +
            "FROM Flight  " +
            "WHERE departureAirportIATACode=:iata")
    long countDepartures(@Param("iata") String iata);

    @Query("SELECT COUNT(*) " +
            "FROM Flight  " +
            "WHERE arrivalAirportIATACode=:iata")
    long countArrivals(@Param("iata") String iata);

    List<Flight> findAllByDepartureAirportIATACode(String iata);
    List<Flight> findAllByArrivalAirportIATACode(String iata);



}
