package rs.ac.uns.ftn.carDealership.service;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.carDealership.model.dto.CreateVehicle;
import rs.ac.uns.ftn.carDealership.model.dto.UpdateVehicle;
import rs.ac.uns.ftn.carDealership.model.vehicle.*;
import rs.ac.uns.ftn.carDealership.repository.*;

import java.util.*;

@Service

public class VehicleDtoFactory {

    @Autowired
    ModelRepository modelRepository;
    @Autowired
    AccesoriesRepository accesoriesRepository;
    @Autowired
    EngineRepository engineRepository;
    @Autowired
    EngineSpecificationRepository engineSpecificationRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    VehicleRepository vehicleRepository;

    public Vehicle buildVehicle(CreateVehicle dto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(UUID.randomUUID());
        vehicle.setModel(buildModel(dto.getModel()));
        vehicle.setAccessories(buildAccessories(dto.getAccessories()));
        vehicle.setNumberOfSeats(Integer.parseInt(dto.getNumberOfSeats()));
        vehicle.setNumberOfDoors(Integer.parseInt(dto.getNumberOfDoors()));
        vehicle.setProductionYear(Integer.parseInt(dto.getYear()));
        vehicle.setMileage(Integer.parseInt(dto.getMileage()));
        vehicle.setAirConditioning(dto.getAirConditioning());
        vehicle.setInteriorMaterial(dto.getInteriorMaterial());
        vehicle.setGearBoxType(dto.getGearBox());
        vehicle.setCarBody(dto.getCarBody());
        vehicle.setCarBodyColor(dto.getColor());
        vehicle.setVehicle_status("Available");
        vehicle.setChassisNumber(dto.getChassisNumber());
        vehicle.setPrice(Integer.parseInt(dto.getPrice()));
        EngineSpecification engineSpecification = buildEngineSpecification(dto);
        Engine engine = buildEngine(dto);
        engine.setEngineSpecification(engineSpecification);
        vehicle.setEngine(engine);
        vehicle.setImages(buildImages(dto.getImages()));
        return vehicle;
    }

    public Vehicle buildVehicleForUpdate(UpdateVehicle dto) {
        Vehicle vehicle = vehicleRepository.getById(UUID.fromString(dto.getVehicleId()));
        vehicle.setModel(buildModelFromName(dto.getModel()));
        vehicle.setAccessories(buildAccessories(dto.getAccessories()));
        vehicle.setNumberOfSeats(Integer.parseInt(dto.getNumberOfSeats()));
        vehicle.setNumberOfDoors(Integer.parseInt(dto.getNumberOfDoors()));
        vehicle.setProductionYear(Integer.parseInt(dto.getYear()));
        vehicle.setMileage(Integer.parseInt(dto.getMileage()));
        vehicle.setAirConditioning(dto.getAirConditioning());
        vehicle.setInteriorMaterial(dto.getInteriorMaterial());
        vehicle.setGearBoxType(dto.getGearBox());
        vehicle.setCarBody(dto.getCarBody());
        vehicle.setCarBodyColor(dto.getColor());
        vehicle.setVehicle_status("Available");
        vehicle.setChassisNumber(dto.getChassisNumber());
        vehicle.setPrice(Integer.parseInt(dto.getPrice()));
        EngineSpecification engineSpecification = buildEngineSpecification(dto);
        Engine engine = buildEngine(dto);
        engine.setEngineSpecification(engineSpecification);
        vehicle.setEngine(engine);
        return vehicle;
    }

    private Set<ImageModel> buildImages(ArrayList<String> images) {
        Set<ImageModel> imageModels = new HashSet<>();
        for (String image : images) {
            imageModels.add(imageRepository.findByName(image).get());
        }
        return imageModels;
    }

    private Set<Accessories> buildAccessories(ArrayList<String> accessories) {
        Set<Accessories> accessoriesList = new HashSet<>();
        for (String accessory : accessories) {
            accessoriesList.add(accesoriesRepository.getAccessoriesByName(accessory));
        }
        return accessoriesList;
    }

    private Model buildModelFromName(String modelName) {
        return modelRepository.getModelByName(modelName);
    }
    
    private Model buildModel(String modelId) {
        return modelRepository.getModelById(UUID.fromString(modelId));
    }

    private Engine buildEngine(CreateVehicle dto) {
        Engine engine = new Engine();
        engine.setEngineNumber(dto.getEngineNumber());
        engineRepository.save(engine);
        return engine;
    }

    private Engine buildEngine(UpdateVehicle dto) {
        Engine engine = new Engine();
        engine.setEngineNumber(dto.getEngineNumber());
        engineRepository.save(engine);
        return engine;
    }

    private EngineSpecification buildEngineSpecification(UpdateVehicle dto) {
        EngineSpecification engineSpecification = new EngineSpecification();
        engineSpecification.setEmissionClass(dto.getEmissionClass());
        engineSpecification.setFuelType(dto.getFuel());
        engineSpecification.setDriveType(dto.getDrive());
        engineSpecification.setEnginePower(Integer.parseInt(dto.getPower()));
        engineSpecification.setEngineDisplacment(Integer.parseInt(dto.getCubicCapacity()));
        engineSpecification.setEngineModel(dto.getLandMark());
        engineSpecificationRepository.save(engineSpecification);
        return engineSpecification;
    }

    private EngineSpecification buildEngineSpecification(CreateVehicle dto) {
        EngineSpecification engineSpecification = new EngineSpecification();
        engineSpecification.setEmissionClass(dto.getEmissionClass());
        engineSpecification.setFuelType(dto.getFuel());
        engineSpecification.setDriveType(dto.getDrive());
        engineSpecification.setEnginePower(Integer.parseInt(dto.getPower()));
        engineSpecification.setEngineDisplacment(Integer.parseInt(dto.getCubicCapacity()));
        engineSpecification.setEngineModel(dto.getLandMark());
        engineSpecificationRepository.save(engineSpecification);
        return engineSpecification;
    }
}
