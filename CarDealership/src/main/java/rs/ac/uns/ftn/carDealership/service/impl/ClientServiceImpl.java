package rs.ac.uns.ftn.carDealership.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.carDealership.model.carDealership.TestDrive;
import rs.ac.uns.ftn.carDealership.model.carDealership.TestDriveStatus;
import rs.ac.uns.ftn.carDealership.model.dto.CreateTestDrive;
import rs.ac.uns.ftn.carDealership.model.dto.TestDriveDto;
import rs.ac.uns.ftn.carDealership.model.users.Client;
import rs.ac.uns.ftn.carDealership.repository.ClientRepository;
import rs.ac.uns.ftn.carDealership.repository.TestDriveRepository;
import rs.ac.uns.ftn.carDealership.repository.TestDriveStatusRepository;
import rs.ac.uns.ftn.carDealership.repository.VehicleRepository;
import rs.ac.uns.ftn.carDealership.service.IClientService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TestDriveRepository testDriveRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private TestDriveStatusRepository testDriveStatusRepository;

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void createTestDrive(CreateTestDrive dto) throws ParseException {
        TestDrive testDrive = new TestDrive();
        testDrive.setClient(clientRepository.getById(UUID.fromString(dto.getClientId())));
        testDrive.setVehicle(vehicleRepository.getById(UUID.fromString(dto.getVehicleId())));
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        testDrive.setDateOfTestDrive(format.parse(dto.getDateOfTestDrive()));
        TestDriveStatus testDriveStatus = new TestDriveStatus();
        testDriveStatus.setDateOfCreation(format.parse(String.valueOf(LocalDateTime.now())));
        testDriveStatus.setStatus("Kreirana");
        testDriveRepository.save(testDrive);
        testDriveStatus.setTestDrive(testDrive);
        testDriveStatusRepository.save(testDriveStatus);
    }

    @Override
    public ArrayList<TestDriveDto> getAllTestDrives(String clientId) {
        System.out.println("********************** SERVIS **********************");
        ArrayList<TestDrive> testDrives = this.testDriveRepository.findTestDriveByClient(UUID.fromString(clientId));
        ArrayList<TestDriveDto> dtos = new ArrayList<>();
        for (TestDrive td : testDrives) {
            TestDriveDto dto = new TestDriveDto();
            dto.setDate(td.getDateOfTestDrive().toString());
            dto.setMark(td.getVehicle().getModel().getMark().getName());
            dto.setModel(td.getVehicle().getModel().getName());
            dto.setLandMark(td.getVehicle().getEngine().getEngineSpecification().getEngineModel());
            dto.setStatus(this.testDriveStatusRepository.findTestDriveStatusByTestDrive_TestDriveId(td.getTestDriveId()).getStatus());
            dtos.add(dto);
        }
        return dtos;
    }
}
