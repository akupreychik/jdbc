package com.javarush.models.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Flights {
  private Integer flightId;
  private String flightNo;
  private ZonedDateTime scheduledDeparture;
  private ZonedDateTime scheduledArrival;
  private String departureAirport;
  private String arrivalAirport;
  private String status;
  private String aircraftCode;
  private ZonedDateTime actualDeparture;
  private ZonedDateTime actualArrival;
}