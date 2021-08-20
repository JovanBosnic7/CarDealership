package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.vehicle.Accessories;

import java.util.UUID;

public interface AccesoriesRepository extends JpaRepository<Accessories, UUID> {
    Accessories getAccessoriesByName(String name);
}
