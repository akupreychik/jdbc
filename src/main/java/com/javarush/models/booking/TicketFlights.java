package com.javarush.models.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketFlights {
  private String ticketNo;
  private Long flightId;
  private String fareConditions;
  private Double amount;
}