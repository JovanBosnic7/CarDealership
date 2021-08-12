package rs.ac.uns.ftn.carDealership.model.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthRequest {
    public String username;
    public String password;
}
