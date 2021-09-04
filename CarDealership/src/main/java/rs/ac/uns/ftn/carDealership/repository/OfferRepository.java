package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.carDealership.Offer;

import java.util.ArrayList;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<Offer, UUID> {
    Offer findByReservation_ReservationId(UUID id);
}
