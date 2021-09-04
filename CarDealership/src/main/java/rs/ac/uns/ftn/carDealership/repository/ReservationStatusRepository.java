package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.carDealership.ReservationStatus;

import java.util.UUID;

public interface ReservationStatusRepository extends JpaRepository<ReservationStatus, UUID> {
}
