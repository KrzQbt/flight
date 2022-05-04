package com.kk.flight.service;

import com.kk.flight.api.dto.IataResponse;
import com.kk.flight.api.dto.WeightResponse;

public interface IInfoService {
    WeightResponse getWeightForFlightAndDate(String flightNo, String date);
    IataResponse getAirportInfo(String iata);
}
