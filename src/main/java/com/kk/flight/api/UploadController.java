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

        
        flights = Collections.unmodifiableList(flights);
        uploadService.saveFlightList(flights);

    }
    @PostMapping("/upload/shipments")
    void uploadShipments(@RequestBody List<ShipmentPerFlightDto> shipments){

        
        shipments = Collections.unmodifiableList(shipments);
        uploadService.saveShipmentList(shipments);
    }


}
