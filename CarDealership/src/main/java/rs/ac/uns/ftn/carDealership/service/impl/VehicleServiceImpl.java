package rs.ac.uns.ftn.carDealership.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.carDealership.model.vehicle.Mark;
import rs.ac.uns.ftn.carDealership.model.vehicle.Model;
import rs.ac.uns.ftn.carDealership.repository.MarkRepository;
import rs.ac.uns.ftn.carDealership.repository.ModelRepository;
import rs.ac.uns.ftn.carDealership.service.IVehicleService;

import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements IVehicleService {
    @Autowired
    MarkRepository marksRepository;
    @Autowired
    ModelRepository modelRepository;

    public List<Mark> getAllMarks() {
        return marksRepository.findAll();
    }

    public List<Model> getAllModelsByMark(String markId) {
        return modelRepository.getModelsByMark_MarkId(UUID.fromString(markId));
    }
}
