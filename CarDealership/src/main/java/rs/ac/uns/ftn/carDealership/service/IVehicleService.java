package rs.ac.uns.ftn.carDealership.service;

import rs.ac.uns.ftn.carDealership.model.dto.CreateVehicle;
import rs.ac.uns.ftn.carDealership.model.vehicle.Mark;
import rs.ac.uns.ftn.carDealership.model.vehicle.Model;
import rs.ac.uns.ftn.carDealership.model.vehicle.Vehicle;
import java.util.List;

public interface IVehicleService {
    List<Mark> getAllMarks();
    List<Model> getAllModelsByMark(String markId);
    Vehicle createVehicle(CreateVehicle dto);
}
