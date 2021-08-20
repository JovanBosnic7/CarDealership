package rs.ac.uns.ftn.carDealership.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class CreateVehicle {
    String mark;
    String model;
    String carBody;
    String year;
    String fuel;
    String landMark;
    String cubicCapacity;
    String power;
    String mileage;
    String emissionClass;
    String drive;
    String gearBox;
    String numberOfDoors;
    String numberOfSeats;
    String airConditioning;
    String color;
    String interiorMaterial;
    String price;
    String engineNumber;
    String chassisNumber;
    ArrayList<String> accessories;
    ArrayList<String> images;
}
