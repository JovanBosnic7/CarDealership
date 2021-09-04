package rs.ac.uns.ftn.carDealership.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateReservation {
    String vehicleId;
    String clientId;
    String offeredPrice;
    String payment;
    String date;
}
