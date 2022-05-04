package com.kk.flight.service;

import com.kk.flight.api.dto.FlightDto;
import com.kk.flight.api.dto.ShipmentPerFlightDto;

import java.util.List;

public interface IUploadService {
    void saveFlightList(List<FlightDto> flightDtos);
    void saveShipmentList(List<ShipmentPerFlightDto> shipmentDtos);
}
