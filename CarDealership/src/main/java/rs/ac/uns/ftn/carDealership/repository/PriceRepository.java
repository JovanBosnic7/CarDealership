package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.vehicle.Price;

import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {
}
