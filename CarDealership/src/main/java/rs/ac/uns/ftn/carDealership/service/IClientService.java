package rs.ac.uns.ftn.carDealership.service;

import rs.ac.uns.ftn.carDealership.model.dto.CreateReservation;
import rs.ac.uns.ftn.carDealership.model.dto.CreateTestDrive;
import rs.ac.uns.ftn.carDealership.model.dto.ReservationDto;
import rs.ac.uns.ftn.carDealership.model.dto.TestDriveDto;
import rs.ac.uns.ftn.carDealership.model.users.Client;

import java.text.ParseException;
import java.util.ArrayList;

public interface IClientService {
    Client save(Client client);

    void createTestDrive(CreateTestDrive dto) throws ParseException;

    void cancelTestDrive(String testDriveId);

    ArrayList<TestDriveDto> getAllTestDrives(String clientId);

    ArrayList<TestDriveDto> getTestDrives();

    void closeTestDrive(String testDriveId);

    void createReservation(CreateReservation dto) throws ParseException;

    ArrayList<ReservationDto> getReservations(String clientId);

    ArrayList<ReservationDto> getAllReservations();

    void cancelReservation(String reservationId);

    void closeReservation(String reservationId);

    void acceptOffer(String reservationId);

    void declineOffer(String reservationId);
}
