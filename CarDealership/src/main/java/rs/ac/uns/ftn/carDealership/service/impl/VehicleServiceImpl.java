package rs.ac.uns.ftn.carDealership.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.carDealership.model.dto.CreateVehicle;
import rs.ac.uns.ftn.carDealership.model.vehicle.*;
import rs.ac.uns.ftn.carDealership.repository.*;
import rs.ac.uns.ftn.carDealership.service.IVehicleService;
import rs.ac.uns.ftn.carDealership.service.VehicleDtoFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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


}
