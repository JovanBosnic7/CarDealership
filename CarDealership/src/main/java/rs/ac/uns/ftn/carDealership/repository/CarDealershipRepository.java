package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.carDealership.CarDealership;

import java.util.UUID;

public interface CarDealershipRepository extends JpaRepository<CarDealership, UUID> {
}
