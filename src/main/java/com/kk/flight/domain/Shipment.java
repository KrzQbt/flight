package com.kk.flight.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long shipmentId;
    private Double weight; // stored in kg
    private Integer pieces;
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    Flight flight;

    public enum Type{
        baggage,
        cargo
    }
}
