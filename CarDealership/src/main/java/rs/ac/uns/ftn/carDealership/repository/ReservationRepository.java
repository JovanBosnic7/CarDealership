package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.carDealership.Reservation;

import java.util.ArrayList;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    ArrayList<Reservation> findAllByClient_UserId(UUID id);

}
