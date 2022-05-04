package com.kk.flight.api.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightListDto {
    List<FlightDto> flights;

}
