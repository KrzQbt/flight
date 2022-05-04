package com.kk.flight.api;

import com.kk.flight.api.dto.IataResponse;
import com.kk.flight.api.dto.WeightResponse;
import com.kk.flight.service.IInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class InfoController {
    private final IInfoService iInfoService;

    @GetMapping("/weight")
    WeightResponse getWeightForFlightAndDate(@RequestParam Map<String,String> allParams){
        return iInfoService.getWeightForFlightAndDate(allParams.get("flightNo"),allParams.get("date"));
    }
//    @GetMapping("/total")
//    IataResponse getAirportStats(@RequestParam String iata){
//
//        return iInfoService.getAirportInfo(iata);
//    }

    @GetMapping("/total")
    IataResponse getAirportStats(@RequestParam Map<String,String> allParams){

        return iInfoService.getAirportInfo(allParams.get("iata"),allParams.get("date"));
    }



}
