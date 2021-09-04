package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.carDealership.OfferStatus;

import java.util.UUID;

public interface OfferStatusRepository extends JpaRepository<OfferStatus, UUID> {
}
