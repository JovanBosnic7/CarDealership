package rs.ac.uns.ftn.carDealership.service;


import rs.ac.uns.ftn.carDealership.model.auth.AuthRequest;
import rs.ac.uns.ftn.carDealership.model.auth.AuthResponse;

public interface IAuthenticationService {
    AuthResponse login(AuthRequest dto);
}
