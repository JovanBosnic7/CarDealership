package rs.ac.uns.ftn.carDealership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.carDealership.model.auth.AuthRequest;
import rs.ac.uns.ftn.carDealership.service.impl.AuthenticationServiceImpl;

@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    @Autowired
    AuthenticationServiceImpl authService;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest dto) {
        return new ResponseEntity<>(authService.login(dto), HttpStatus.OK);
    }
}
