package com.kk.flight.api.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class IataResponse {
    Long totalDepartingFlights;
    Long totalArrivingFlights;
    Long totalArrivingBaggage;
    Long totalDepartingBaggage;
}
