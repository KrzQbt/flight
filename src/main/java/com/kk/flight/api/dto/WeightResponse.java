package com.kk.flight.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;


@Builder
@Value
public class WeightResponse {
    String weightUnit = "kg";
    Double cargoWeight;
    Double baggageWeight;
    Double totalWeight;

}
