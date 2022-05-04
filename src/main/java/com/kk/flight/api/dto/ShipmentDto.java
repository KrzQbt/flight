package com.kk.flight.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class ShipmentDto {
    Long id;
    Double weight;
    String weightUnit;
    Integer pieces;
}
