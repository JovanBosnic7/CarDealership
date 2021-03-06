package rs.ac.uns.ftn.carDealership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.carDealership.model.carDealership.CarDealership;
import rs.ac.uns.ftn.carDealership.model.carDealership.Promotion;
import rs.ac.uns.ftn.carDealership.model.carDealership.WorkTime;
import rs.ac.uns.ftn.carDealership.model.dto.CreateClient;
import rs.ac.uns.ftn.carDealership.model.dto.CreateFeedback;
import rs.ac.uns.ftn.carDealership.model.dto.CreateReservation;
import rs.ac.uns.ftn.carDealership.model.dto.CreateTestDrive;
import rs.ac.uns.ftn.carDealership.repository.*;
import rs.ac.uns.ftn.carDealership.service.ClientDtoFactory;
import rs.ac.uns.ftn.carDealership.service.IClientService;

import java.text.ParseException;
import java.util.UUID;

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
    PromotionRepository promotionRepository;
    @Autowired
    CarDealershipRepository carDealershipRepository;
    @Autowired
    PriceRepository priceRepository;
    @Autowired
    WorkTimeRepository workTimeRepository;
    @Autowired
    FeedbackRepository feedbackRepository;

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
        WorkTime wt = this.workTimeRepository.save(carDealership.getWorkTime());
        carDealership.setWorkTime(wt);
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

    @GetMapping("/getFeedbacks/{vehicleId}")
    public ResponseEntity<?> getFeedbacksForVehicle(@PathVariable String vehicleId) {

        return new ResponseEntity<>(feedbackRepository.findAllByVehicle_VehicleId(UUID.fromString(vehicleId)), HttpStatus.OK);
    }

    @GetMapping("/deletePromotion/{promotionId}")
    public ResponseEntity<?> deletePromotion(@PathVariable String promotionId) {
        Promotion promotion = promotionRepository.getById(UUID.fromString(promotionId));
        promotionRepository.delete(promotion);
        return new ResponseEntity<>("Promotion deleted", HttpStatus.OK);
    }

    @GetMapping("/getAllReservations")
    public ResponseEntity<?> getAllReservations() {
        return new ResponseEntity<>(clientService.getAllReservations(), HttpStatus.OK);
    }

    @PostMapping("/cancelTestDrive")
    public ResponseEntity<?> cancelTestDrive(@RequestBody String testDriveId) {
        this.clientService.cancelTestDrive(testDriveId);
        return new ResponseEntity<>("Test drive successfully canceled", HttpStatus.OK);
    }

    @PostMapping("/cancelReservation")
    public ResponseEntity<?> cancelReservation(@RequestBody String reservationId) {
        this.clientService.cancelReservation(reservationId);
        return new ResponseEntity<>("Reservation successfully canceled", HttpStatus.OK);
    }

    @PostMapping("/closeReservation")
    public ResponseEntity<?> closeReservation(@RequestBody String reservationId) {
        this.clientService.closeReservation(reservationId);
        return new ResponseEntity<>("Reservation successfully closed", HttpStatus.OK);
    }

    @PostMapping("/acceptOffer")
    public ResponseEntity<?> acceptOffer(@RequestBody String reservationId) {
        this.clientService.acceptOffer(reservationId);
        return new ResponseEntity<>("Offer successfully accepted", HttpStatus.OK);
    }

    @PostMapping("/declineOffer")
    public ResponseEntity<?> declineOffer(@RequestBody String reservationId) {
        this.clientService.declineOffer(reservationId);
        return new ResponseEntity<>("Offer successfully declined", HttpStatus.OK);
    }

    @GetMapping("/getAllTestDrives")
    public ResponseEntity<?> getAllTestDrives() {
        return new ResponseEntity<>(clientService.getTestDrives(), HttpStatus.OK);
    }

    @PostMapping("/closeTestDrive")
    public ResponseEntity<?> CloseTestDrive(@RequestBody String testDriveId) {
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

    @PostMapping("/createPromotion")
    public ResponseEntity<?> createPromotion(@RequestBody Promotion promotion) {
        System.out.println(promotion.getName());
        System.out.println(promotion.getDescription());
        promotionRepository.save(promotion);
        return new ResponseEntity<>("Promotion successfully created", HttpStatus.OK);
    }

    @GetMapping("/getAllPromotions")
    public ResponseEntity<?> getAllPromotions() {
        return new ResponseEntity<>(promotionRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/createFeedback")
    public ResponseEntity<?> createFeedback(@RequestBody CreateFeedback dto) throws ParseException {
        this.clientService.createFeedback(dto);
        return new ResponseEntity<>("Feedback successfully created", HttpStatus.OK);
    }


}
