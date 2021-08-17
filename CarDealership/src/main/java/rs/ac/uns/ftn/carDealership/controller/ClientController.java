package rs.ac.uns.ftn.carDealership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.carDealership.model.dto.CreateClient;
import rs.ac.uns.ftn.carDealership.service.ClientDtoFactory;
import rs.ac.uns.ftn.carDealership.service.ClientService;

@RestController
@RequestMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ClientDtoFactory clientDtoFactory;

    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody CreateClient dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return new ResponseEntity<>(clientService.save(clientDtoFactory.buildClientFromDto(dto)), HttpStatus.CREATED);
    }
}
