package rs.ac.uns.ftn.carDealership.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.carDealership.model.dto.CreateAction;
import rs.ac.uns.ftn.carDealership.model.dto.CreateVehicle;
import rs.ac.uns.ftn.carDealership.model.dto.UpdateVehicle;
import rs.ac.uns.ftn.carDealership.model.vehicle.*;
import rs.ac.uns.ftn.carDealership.repository.*;
import rs.ac.uns.ftn.carDealership.service.IVehicleService;
import rs.ac.uns.ftn.carDealership.service.VehicleDtoFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

@Service
public class VehicleServiceImpl implements IVehicleService {
    @Autowired
    MarkRepository marksRepository;
    @Autowired
    ModelRepository modelRepository;
    @Autowired
    EngineSpecificationRepository engineSpecificationRepository;
    @Autowired
    EngineRepository engineRepository;
    @Autowired
    AccesoriesRepository accesoriesRepository;
    @Autowired
    VehicleRepository vehicleRepository;
    @Autowired
    VehicleDtoFactory vehicleDtoFactory;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    PriceRepository priceRepository;

    public List<Mark> getAllMarks() {
        return marksRepository.findAll();
    }

    public List<Model> getAllModelsByMark(String markId) {
        return modelRepository.getModelsByMark_MarkId(UUID.fromString(markId));
    }

    @Override
    public Vehicle updateVehicle(UpdateVehicle dto) {
        Vehicle vehicle = vehicleDtoFactory.buildVehicleForUpdate(dto);
        Vehicle v = vehicleRepository.saveAndFlush(vehicle);
        return vehicle;
    }
    @Override
    public Vehicle createVehicle(CreateVehicle dto) {
        Vehicle vehicle = vehicleDtoFactory.buildVehicle(dto);
        Vehicle v = vehicleRepository.save(vehicle);
        savePrice(v);
        return vehicle;
    }
    private void savePrice(Vehicle vehicle) {
        Price price = new Price();
        price.setPrice(vehicle.getPrice());
        price.setDateOfSetting(new Date());
        price.setVehicle(vehicle);
        priceRepository.save(price);
    }
    @Override
    public Vehicle getVehicleById(String vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(UUID.fromString(vehicleId)).get();
        for (ImageModel imageModel : vehicle.getImages()) {
            imageModel.setPicByte(decompressBytes(imageModel.getPicByte()));
        }
        return vehicle;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) vehicleRepository.findAll();
        for (Vehicle vehicle : vehicles) {
            for (ImageModel imageModel : vehicle.getImages()) {
                imageModel.setPicByte(decompressBytes(imageModel.getPicByte()));
            }
        }
        return vehicles;
    }

    @Override
    public void save(Vehicle vehicle) {
        this.vehicleRepository.save(vehicle);
    }

    @Override
    public void sellVehicle(String vehicleId) {
        Vehicle vehicle = vehicleRepository.getById(UUID.fromString(vehicleId));
        vehicle.setVehicle_status("Sold");
        vehicleRepository.save(vehicle);
    }

    @Override
    public void createAction(CreateAction createAction) {
        Vehicle vehicle = vehicleRepository.findById(UUID.fromString(createAction.getVehicleId())).get();
        vehicle.setOldPrice(vehicle.getPrice());
        vehicle.setPrice(calculatePrice(vehicle.getPrice(), createAction.getActionPrecentage()));
        vehicle.setHasAction(true);
        Price price = new Price();
        price.setPrice(vehicle.getPrice());
        price.setVehicle(vehicle);
        price.setDateOfSetting(new Date());
        this.vehicleRepository.save(vehicle);
        priceRepository.save(price);
    }

    private int calculatePrice(int vehiclePrice, String actionPrecentage) {
        int precentage = Integer.parseInt(actionPrecentage);
        return vehiclePrice - (vehiclePrice * precentage / 100);
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
