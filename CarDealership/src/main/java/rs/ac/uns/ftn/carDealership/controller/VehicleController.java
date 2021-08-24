package rs.ac.uns.ftn.carDealership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.carDealership.model.dto.CreateVehicle;
import rs.ac.uns.ftn.carDealership.model.vehicle.ImageModel;
import rs.ac.uns.ftn.carDealership.model.vehicle.Vehicle;
import rs.ac.uns.ftn.carDealership.repository.ImageRepository;
import rs.ac.uns.ftn.carDealership.repository.VehicleRepository;
import rs.ac.uns.ftn.carDealership.service.IVehicleService;
import rs.ac.uns.ftn.carDealership.service.ImageFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleController {
    @Autowired
    IVehicleService vehicleService;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ImageFactory imageFactory;
    @Autowired
    VehicleRepository vehicleRepository;

    @GetMapping("/getMarks")
    public ResponseEntity<?> getMarks() {
        return new ResponseEntity<>(vehicleService.getAllMarks(), HttpStatus.OK);
    }

    @GetMapping("/getModels/{markId}")
    public ResponseEntity<?> getModels(@PathVariable String markId) {
        return new ResponseEntity<>(vehicleService.getAllModelsByMark(markId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createVehicle(@RequestBody CreateVehicle createVehicle) {
        return new ResponseEntity<>(vehicleService.createVehicle(createVehicle), HttpStatus.CREATED);
    }

    @PostMapping("/uploadImages")
    public void uploadImage(@RequestBody ArrayList<MultipartFile> images) throws IOException {
        imageFactory.uploadImages(images);
    }

    @PostMapping("/sellVehicle")
    public ResponseEntity<?> uploadImage(@RequestBody String vehicleId) {
        Vehicle vehicle = vehicleRepository.getById(UUID.fromString(vehicleId));
        vehicle.setVehicle_status("Sold");
        vehicleRepository.save(vehicle);
        return new ResponseEntity<>("Vehicle successfully sold", HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/getById/{vehicleId}")
    public ResponseEntity<?> getVehicleById(@PathVariable String vehicleId) {
        return new ResponseEntity<>(vehicleService.getVehicleById(vehicleId), HttpStatus.OK);
    }

}
