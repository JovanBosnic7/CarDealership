package rs.ac.uns.ftn.carDealership.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.carDealership.service.IVehicleService;

@RestController
@RequestMapping(value = "/api/vehicles", produces = MediaType.APPLICATION_JSON_VALUE)
public class VehicleController {
    @Autowired
    IVehicleService vehicleService;

    @GetMapping("/getMarks")
    public ResponseEntity<?> getMarks() {
        return new ResponseEntity<>(vehicleService.getAllMarks(), HttpStatus.OK);
    }

    @GetMapping("/getModels/{markId}")
    public ResponseEntity<?> getModels(@PathVariable String markId) {
        return new ResponseEntity<>(vehicleService.getAllModelsByMark(markId), HttpStatus.OK);
    }

}
