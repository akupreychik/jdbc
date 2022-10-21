package com.javarush.models.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AircraftsData {
    private String aircraftCode;
    private String model;
    private String range;
}
