package rs.ac.uns.ftn.carDealership.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class CreateClient {
    UUID userId;
    String name;
    String lastName;
    String username;
    String password;
}
