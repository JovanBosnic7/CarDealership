package rs.ac.uns.ftn.carDealership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.carDealership.model.carDealership.CarDealership;
import rs.ac.uns.ftn.carDealership.model.dto.CreateClient;
import rs.ac.uns.ftn.carDealership.repository.CarDealershipRepository;
import rs.ac.uns.ftn.carDealership.service.ClientDtoFactory;
import rs.ac.uns.ftn.carDealership.service.IClientService;

@RestController
@RequestMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {
    @Autowired
    IClientService clientService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ClientDtoFactory clientDtoFactory;

    @Autowired
    CarDealershipRepository carDealershipRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody CreateClient dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return new ResponseEntity<>(clientService.save(clientDtoFactory.buildClientFromDto(dto)), HttpStatus.CREATED);
    }

    @GetMapping("/getCarDealership")
    public ResponseEntity<?> getCarDealership() {
        return new ResponseEntity<>(carDealershipRepository.findAll().get(0), HttpStatus.OK);
    }

    @PostMapping("/updateCarDealership")
    public ResponseEntity<?> updateCarDealership(@RequestBody CarDealership carDealership) {
        this.carDealershipRepository.save(carDealership);
        return new ResponseEntity<>("Car dealership successfully updated", HttpStatus.OK);
    }
}
