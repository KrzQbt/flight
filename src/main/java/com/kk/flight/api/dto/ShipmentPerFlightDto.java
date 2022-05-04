package com.kk.flight.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class ShipmentPerFlightDto {
    Long flightId;
    List<ShipmentDto> baggage;
    List<ShipmentDto> cargo;
}
