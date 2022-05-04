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
    public IataResponse getAirportInfo(String iata, String date) {
        List<Flight> departingFlights =
                flightRepository.findAllByDepartureAirportIATACodeAndDepartureDate(iata,date);
        // I assume that plane arrives the same day it departs, but it may not be true
        // there is no flight time given, so I assume that
        List<Flight> arrivingFlights =
                flightRepository.findAllByArrivalAirportIATACodeAndDepartureDate(iata, date);

        long departingBaggage = 0;
        long arrivingBaggage = 0;

        for (Flight f : departingFlights)
            departingBaggage += shipmentRepository.countBaggagePiecesForFlightId(f.getFlightId());
        for (Flight f : arrivingFlights)
            arrivingBaggage += shipmentRepository.countBaggagePiecesForFlightId(f.getFlightId());


        return IataResponse.builder()
                .totalDepartingFlights((long) departingFlights.size())
                .totalArrivingFlights((long) arrivingFlights.size())
                .totalArrivingBaggage(arrivingBaggage)
                .totalDepartingBaggage(departingBaggage)
                .build();
    }
}
