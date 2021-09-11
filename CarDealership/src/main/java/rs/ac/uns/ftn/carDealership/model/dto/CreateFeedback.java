package rs.ac.uns.ftn.carDealership.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateFeedback {
    String clientId;
    String vehicleId;
    String description;
}
