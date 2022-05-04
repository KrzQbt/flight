package com.kk.flight.database;

import com.kk.flight.domain.Flight;
import com.kk.flight.domain.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findAllByFlightAndType(Flight flight, Shipment.Type type);

    @Query("SELECT SUM(pieces) " +
            "FROM Shipment  " +
            "WHERE flight_flight_id=:fid " +
            "AND type='baggage'")
    long countBaggagePiecesForFlightId(@Param("fid") Long flightId);
}
