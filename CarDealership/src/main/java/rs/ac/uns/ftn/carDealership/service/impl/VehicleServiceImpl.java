package rs.ac.uns.ftn.carDealership.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.carDealership.model.dto.CreateVehicle;
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

    public List<Mark> getAllMarks() {
        return marksRepository.findAll();
    }

    public List<Model> getAllModelsByMark(String markId) {
        return modelRepository.getModelsByMark_MarkId(UUID.fromString(markId));
    }

    @Override
    public Vehicle createVehicle(CreateVehicle dto) {
        Vehicle vehicle = vehicleDtoFactory.buildVehicle(dto);
        this.vehicleRepository.save(vehicle);
        return vehicle;
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
