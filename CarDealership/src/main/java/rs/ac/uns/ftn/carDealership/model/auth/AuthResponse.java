package rs.ac.uns.ftn.carDealership.model.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    public UUID id;
    public String username;
    public String token;
    public String role;
    public int expiresIn;
}
