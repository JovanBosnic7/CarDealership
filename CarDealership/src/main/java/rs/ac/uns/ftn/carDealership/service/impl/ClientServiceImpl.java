package rs.ac.uns.ftn.carDealership.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.carDealership.model.carDealership.*;
import rs.ac.uns.ftn.carDealership.model.dto.*;
import rs.ac.uns.ftn.carDealership.model.users.Client;
import rs.ac.uns.ftn.carDealership.model.vehicle.Vehicle;
import rs.ac.uns.ftn.carDealership.repository.*;
import rs.ac.uns.ftn.carDealership.service.IClientService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationStatusRepository reservationStatusRepository;
    @Autowired
    private OfferStatusRepository offerStatusRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;

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
    public void cancelTestDrive(String testDriveId) {
        TestDriveStatus tds = this.testDriveStatusRepository.findTestDriveStatusByTestDrive_TestDriveId(UUID.fromString(testDriveId));
        tds.setStatus("Otkazana");
        testDriveStatusRepository.save(tds);
    }

    @Override
    public ArrayList<TestDriveDto> getAllTestDrives(String clientId) {
        ArrayList<TestDrive> testDrives = this.testDriveRepository.findTestDriveByClient(UUID.fromString(clientId));
        return buildTestDriveDto(testDrives);
    }

    @Override
    public ArrayList<TestDriveDto> getTestDrives() {
        ArrayList<TestDrive> testDrives = (ArrayList<TestDrive>) this.testDriveRepository.findAll();
        return buildTestDriveDto(testDrives);
    }

    @Override
    public void closeTestDrive(String testDriveId) {
        TestDriveStatus tds = this.testDriveStatusRepository.findTestDriveStatusByTestDrive_TestDriveId(UUID.fromString(testDriveId));
        tds.setStatus("Završena");
        testDriveStatusRepository.save(tds);
    }

    @Override
    public void createReservation(CreateReservation dto) throws ParseException {
        Reservation reservation = new Reservation();
        Vehicle vehicle = this.vehicleRepository.getById(UUID.fromString(dto.getVehicleId()));
        reservation.setVehicle(vehicle);
        Client client = this.clientRepository.getById(UUID.fromString(dto.getClientId()));
        reservation.setClient(client);
        reservation.setDateOfValidity(new SimpleDateFormat("yyyy-mm-dd").parse(dto.getDate()));
        reservation.setTypeOfPayment(dto.getPayment());
        reservation.setReservationStatus(buildReservationStatus());
        Reservation res = reservationRepository.save(reservation);
        if (!dto.getOfferedPrice().isEmpty()) {
            buildOffer(dto.getOfferedPrice(), res);
        }
    }

    @Override
    public ArrayList<ReservationDto> getReservations(String clientId) {
        ArrayList<Reservation> reservations = reservationRepository.findAllByClient_UserId(UUID.fromString(clientId));
        return buildReservationDtos(reservations);
    }

    @Override
    public ArrayList<ReservationDto> getAllReservations() {
        return buildReservationDtos((ArrayList<Reservation>) reservationRepository.findAll());
    }

    @Override
    public void cancelReservation(String reservationId) {
        Reservation reservation = getReservation(reservationId);
        ReservationStatus res = createReservationStatus("Otkazana");
        reservation.setReservationStatus(res);
        createOffer(reservation.getReservationId(), "Otkazana");
        reservationRepository.save(reservation);
    }

    @Override
    public void closeReservation(String reservationId) {
        Reservation reservation = getReservation(reservationId);
        ReservationStatus res = createReservationStatus("Zatvorena");
        reservation.setReservationStatus(res);
        createOffer(reservation.getReservationId(), "Zatvorena");
        reservationRepository.save(reservation);
    }

    @Override
    public void acceptOffer(String reservationId) {
        Reservation reservation = getReservation(reservationId);
        createOffer(reservation.getReservationId(), "Prihvaćena");
    }

    @Override
    public void declineOffer(String reservationId) {
        Reservation reservation = getReservation(reservationId);
        createOffer(reservation.getReservationId(), "Odbijena");
    }

    @Override
    public void createFeedback(CreateFeedback dto) {
        Feedback feedback = new Feedback();
        feedback.setDateOfPosting(new Date());
        feedback.setClient(clientRepository.getById(UUID.fromString(dto.getClientId())));
        feedback.setVehicle(vehicleRepository.getById(UUID.fromString(dto.getVehicleId())));
        feedback.setDescription(dto.getDescription());
        feedbackRepository.save(feedback);
    }

    private void createOffer(UUID reservationId, String offerStatus) {
        Offer offer = offerRepository.findByReservation_ReservationId(reservationId);
        if (offer != null) {
            OfferStatus ofs = new OfferStatus();
            ofs.setDateOfCreation(new Date());
            ofs.setStatus(offerStatus);
            offerStatusRepository.save(ofs);
            offer.setOfferStatus(ofs);
            offerRepository.save(offer);
        }
    }

    private ReservationStatus createReservationStatus(String status) {
        ReservationStatus reservationStatus = new ReservationStatus();
        reservationStatus.setStatus(status);
        reservationStatus.setDateOfCreation(new Date());
        return reservationStatusRepository.save(reservationStatus);
    }

    private Reservation getReservation(String reservationId) {
        return reservationRepository.getById(UUID.fromString(reservationId));
    }

    private ArrayList<ReservationDto> buildReservationDtos(ArrayList<Reservation> reservations) {
        ArrayList<ReservationDto> dtos = new ArrayList<>();
        for (Reservation res : reservations) {
            ReservationDto dto = new ReservationDto();
            dto.setId(res.getReservationId().toString());
            dto.setClient(res.getClient().getName() + " " + res.getClient().getLastName());
            dto.setMark(res.getVehicle().getModel().getMark().getName());
            dto.setModel(res.getVehicle().getModel().getName());
            dto.setLandMark(res.getVehicle().getEngine().getEngineSpecification().getEngineModel());
            dto.setDate(res.getDateOfValidity().toString());
            dto.setStatus(res.getReservationStatus().getStatus());
            dto.setVehicleId(res.getVehicle().getVehicleId().toString());
            Offer offer = offerRepository.findByReservation_ReservationId(res.getReservationId());
            if (offer != null) {
                dto.setOfferedPrice(String.valueOf(offer.getOfferedPrice()));
                dto.setOfferStatus(offer.getOfferStatus().getStatus());
            }
            dtos.add(dto);
        }
        return dtos;
    }

    private void buildOffer(String offeredPrice, Reservation reservation) {
        Offer offer = new Offer();
        offer.setDateOfOffer(new Date());
        offer.setOfferedPrice(Double.parseDouble(offeredPrice));
        offer.setReservation(reservation);
        offer.setOfferStatus(buildOfferStatus());
        offerRepository.save(offer);
    }

    private OfferStatus buildOfferStatus() {
        OfferStatus offerStatus = new OfferStatus();
        offerStatus.setStatus("Kreirana");
        offerStatus.setDateOfCreation(new Date());
        return offerStatusRepository.save(offerStatus);
    }

    private ReservationStatus buildReservationStatus() {
        ReservationStatus reservationStatus = new ReservationStatus();
        reservationStatus.setStatus("Kreirana");
        reservationStatus.setDateOfCreation(new Date());
        return reservationStatusRepository.save(reservationStatus);
    }

    private ArrayList<TestDriveDto> buildTestDriveDto(ArrayList<TestDrive> testDrives) {
        ArrayList<TestDriveDto> dtos = new ArrayList<>();
        for (TestDrive td : testDrives) {
            TestDriveDto dto = new TestDriveDto();
            dto.setDate(td.getDateOfTestDrive().toString());
            dto.setId(td.getTestDriveId().toString());
            dto.setMark(td.getVehicle().getModel().getMark().getName());
            dto.setModel(td.getVehicle().getModel().getName());
            dto.setLandMark(td.getVehicle().getEngine().getEngineSpecification().getEngineModel());
            dto.setStatus(this.testDriveStatusRepository.findTestDriveStatusByTestDrive_TestDriveId(td.getTestDriveId()).getStatus());
            dto.setClient(td.getClient().getName() + " " + td.getClient().getLastName());
            dto.setVehicleId(td.getVehicle().getVehicleId().toString());
            dtos.add(dto);
        }
        return dtos;
    }
}
