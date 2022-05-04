package com.kk.flight.service.impl;

import com.kk.flight.api.dto.FlightDto;
import com.kk.flight.api.dto.ShipmentDto;
import com.kk.flight.api.dto.ShipmentPerFlightDto;
import com.kk.flight.database.FlightRepository;
import com.kk.flight.database.ShipmentRepository;
import com.kk.flight.domain.Flight;
import com.kk.flight.domain.Shipment;
import com.kk.flight.service.IUploadService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UploadService implements IUploadService {

    // constant to convert lb to kg
    private static final double poundToKg = 0.45;

    private final FlightRepository flightRepository;
    private final ShipmentRepository shipmentRepository;


    @Override
    public void saveFlightList(List<FlightDto> flightDtos) {

        List<Flight> flights = flightDtos.stream()
                .map(UploadService::mapFlightDtoToEntity)
                .collect(Collectors.toList());

        flightRepository.saveAll(flights);



    }

    public void saveShipmentList(List<ShipmentPerFlightDto> shipmentDtos){

        List<Shipment> shipments = new LinkedList<>();
        shipmentDtos.forEach(
                x->{
                    if (flightRepository.findById(x.getFlightId()).isEmpty())
                        throw new RuntimeException("there is no flight with id " + x.getFlightId() +", please verify list");
                    Flight flight = flightRepository.findById(x.getFlightId()).get();
                    for (ShipmentDto load : x.getBaggage())
                        shipments.add(mapShipmentDtoToEntity(load,flight,"baggage"));
                    for (ShipmentDto load : x.getCargo())
                        shipments.add(mapShipmentDtoToEntity(load,flight,"cargo"));
                }
        );

        shipmentRepository.saveAll(shipments);


    }






    static Flight mapFlightDtoToEntity(FlightDto flight){
        if (!isDateFormatValid(flight.getDepartureDate()))
            throw new RuntimeException("date not in proper format");

        return Flight.builder()
                .flightId(flight.getFlightId())
                .flightNumber(flight.getFlightNumber())
                .departureAirportIATACode(flight.getDepartureAirportIATACode())
                .arrivalAirportIATACode(flight.getArrivalAirportIATACode())
                .departureDate(flight.getDepartureDate().substring(0,10))
                .timeWithZone(flight.getDepartureDate().substring(11))
                .build();
    }

    static Shipment mapShipmentDtoToEntity(ShipmentDto shipmentDto, Flight flight, String type){
        if (shipmentDto.getWeight() <=0 || shipmentDto.getPieces() <=0)
            throw new RuntimeException("weight or quantity(pcs) is <=0 for load " +shipmentDto.getId() + "in flight " + flight.getFlightId());

        Double weightInKg;
        // conversion from lb to kg
        if (shipmentDto.getWeightUnit().equals("lb"))
            weightInKg = shipmentDto.getWeight() * poundToKg;
        else if (shipmentDto.getWeightUnit().equals("kg"))
            weightInKg = shipmentDto.getWeight();
        else
            throw new RuntimeException(" weight unit is neither lowercase 'kg' nor 'lb' for flight with id "+ flight.getFlightId());

        return Shipment.builder()
                .shipmentId(shipmentDto.getId())
                .weight(weightInKg)
                .pieces(shipmentDto.getPieces())
                .type(Shipment.Type.valueOf(type))
                .flight(flight)
                .build();
    }

    public static boolean isDateFormatValid(String date){

        // checking if date is in format 2019-07-07T10:11:26-02:00, but not checking if it makes sense
        String pattern = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}-\\d{2}:\\d{2}";
        return date.matches(pattern);
    }


}
