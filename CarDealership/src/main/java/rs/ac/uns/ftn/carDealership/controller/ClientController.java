package rs.ac.uns.ftn.carDealership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.carDealership.model.carDealership.CarDealership;
import rs.ac.uns.ftn.carDealership.model.dto.CreateClient;
import rs.ac.uns.ftn.carDealership.model.dto.CreateReservation;
import rs.ac.uns.ftn.carDealership.model.dto.CreateTestDrive;
import rs.ac.uns.ftn.carDealership.repository.CarDealershipRepository;
import rs.ac.uns.ftn.carDealership.repository.PriceRepository;
import rs.ac.uns.ftn.carDealership.service.ClientDtoFactory;
import rs.ac.uns.ftn.carDealership.service.IClientService;

import java.text.ParseException;

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
    @Autowired
    PriceRepository priceRepository;

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

    @PostMapping("/createTestDrive")
    public ResponseEntity<?> createTestDrive(@RequestBody CreateTestDrive dto) throws ParseException {
        this.clientService.createTestDrive(dto);
        return new ResponseEntity<>("Test drive successfully created", HttpStatus.OK);
    }
    @GetMapping("/getAllTestDrives/{clientId}")
    public ResponseEntity<?> getAllTestDrivesByFlient(@PathVariable String clientId) {
        return new ResponseEntity<>(clientService.getAllTestDrives(clientId), HttpStatus.OK);
    }

    @GetMapping("/getReservations/{clientId}")
    public ResponseEntity<?> getReservationsForClient(@PathVariable String clientId) {
        return new ResponseEntity<>(clientService.getReservations(clientId), HttpStatus.OK);
    }
    @GetMapping("/getAllReservations")
    public ResponseEntity<?> getAllReservations() {
        return new ResponseEntity<>(clientService.getAllReservations(), HttpStatus.OK);
    }

    @PostMapping("/cancelTestDrive")
    public ResponseEntity<?> cancelTestDrive(@RequestBody String testDriveId)  {
        this.clientService.cancelTestDrive(testDriveId);
        return new ResponseEntity<>("Test drive successfully canceled", HttpStatus.OK);
    }
    @GetMapping("/getAllTestDrives")
    public ResponseEntity<?> getAllTestDrives() {
        return new ResponseEntity<>(clientService.getTestDrives(), HttpStatus.OK);
    }
    @PostMapping("/closeTestDrive")
    public ResponseEntity<?> CloseTestDrive(@RequestBody String testDriveId)  {
        this.clientService.closeTestDrive(testDriveId);
        return new ResponseEntity<>("Test drive successfully closed", HttpStatus.OK);
    }

    @GetMapping("/getAllPrices")
    public ResponseEntity<?> getAllPrices() {
        return new ResponseEntity<>(priceRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createReservation")
    public ResponseEntity<?> createReservation(@RequestBody CreateReservation dto) throws ParseException {
        this.clientService.createReservation(dto);
        return new ResponseEntity<>("Reservation successfully created", HttpStatus.OK);
    }

}
