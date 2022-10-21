package com.javarush.models.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bookings {
  private Long bookRef;
  private ZonedDateTime bookDate;
  private Double totalAmount;
}