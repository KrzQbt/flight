package com.kk.flight.service.impl;

import com.kk.flight.api.dto.IataResponse;
import com.kk.flight.api.dto.WeightResponse;
import com.kk.flight.database.FlightRepository;
import com.kk.flight.database.ShipmentRepository;
import com.kk.flight.domain.Flight;
import com.kk.flight.domain.Shipment;
import com.kk.flight.service.IInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class InfoService implements IInfoService {
    private final FlightRepository flightRepository;
    private final ShipmentRepository shipmentRepository;


    @Override
    public WeightResponse getWeightForFlightAndDate(String flightNo, String date) {
        if (flightRepository.findByFlightNumberAndDepartureDate(Long.parseLong(flightNo),date).isEmpty())
            throw new RuntimeException("no such flight at given date");
        Flight flight =
                flightRepository.findByFlightNumberAndDepartureDate(Long.parseLong(flightNo),date).get();

        List<Shipment> cargo = shipmentRepository.findAllByFlightAndType(flight, Shipment.Type.cargo);
        List<Shipment> baggage = shipmentRepository.findAllByFlightAndType(flight, Shipment.Type.baggage);

        Double cargoWeight = cargo.stream()
                .map(Shipment::getWeight)
                .reduce((double) 0, Double::sum);
        Double baggageWeight = baggage.stream()
                .map(Shipment::getWeight)
                .reduce((double) 0, Double::sum);

        return WeightResponse.builder()
                .cargoWeight(cargoWeight)
                .baggageWeight(baggageWeight)
                .totalWeight(cargoWeight+baggageWeight)
                .build();

    }


    @Override
    public IataResponse getAirportInfo(String iata) {

        List<Flight> departingFlights =
                flightRepository.findAllByDepartureAirportIATACode(iata);
        List<Flight> arrivingFlights =
                flightRepository.findAllByArrivalAirportIATACode(iata);

        long departingBaggage = 0;
        long arrivingBaggage = 0;

        for (Flight f : departingFlights)
            departingBaggage += shipmentRepository.countBaggagePiecesForFlightId(f.getFlightId());
        for (Flight f : arrivingFlights)
            arrivingBaggage += shipmentRepository.countBaggagePiecesForFlightId(f.getFlightId());


        return IataResponse.builder()
                .totalDepartingFlights(flightRepository.countDepartures(iata))
                .totalArrivingFlights(flightRepository.countArrivals(iata))
                .totalDepartingBaggage(departingBaggage)
                .totalArrivingBaggage(arrivingBaggage)
                .build();
    }



}
