package rs.ac.uns.ftn.carDealership.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservationDto {
    String id;
    String mark;
    String model;
    String landMark;
    String date;
    String status;
    String client;
    String offeredPrice;
    String offerStatus;
    String vehicleId;
}
