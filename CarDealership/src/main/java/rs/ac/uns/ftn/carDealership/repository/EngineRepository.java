package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.carDealership.model.vehicle.Engine;
import rs.ac.uns.ftn.carDealership.model.vehicle.EngineSpecification;

import java.util.UUID;

public interface EngineRepository extends JpaRepository<Engine, UUID> {
    @Query("select engine from Engine  engine where engine.engineNumber = ?1")
    Engine getByEngineNumber(String engineNumber);
}
