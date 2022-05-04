package com.kk.flight.api;

import com.kk.flight.api.dto.FlightDto;
import com.kk.flight.api.dto.ShipmentPerFlightDto;
import com.kk.flight.service.IUploadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class UploadController {

    private final IUploadService uploadService;

    @PostMapping("/upload/flights")
    void uploadFlights(@RequestBody List<FlightDto> flights){

        // prevents list from further insertion and deletion
        flights = Collections.unmodifiableList(flights);
//        flights.forEach(
//                x -> {
//                    System.out.println(x.getDepartureDate().substring(0,10));
//                    System.out.println(x.getDepartureDate().substring(11));
//                }
//        );

        uploadService.saveFlightList(flights);

    }
    @PostMapping("/upload/shipments")
    void uploadShipments(@RequestBody List<ShipmentPerFlightDto> shipments){

        // prevents list from further insertion and deletion
        shipments = Collections.unmodifiableList(shipments);
//        shipments.forEach( x -> {
//            System.out.println("baggage");
//            x.getBaggage().forEach(System.out::println);
//            System.out.println("cargo");
//            x.getCargo().forEach(System.out::println);
//            System.out.println("next");
//        });
        uploadService.saveShipmentList(shipments);
    }


}
