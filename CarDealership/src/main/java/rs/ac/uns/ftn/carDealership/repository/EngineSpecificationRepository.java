package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.carDealership.model.vehicle.Engine;
import rs.ac.uns.ftn.carDealership.model.vehicle.EngineSpecification;

import java.util.UUID;

public interface EngineSpecificationRepository extends JpaRepository<EngineSpecification, UUID> {
    @Query("select engineSpec from EngineSpecification  engineSpec where engineSpec.engineSpecificationId = ?1")
    EngineSpecification getById(UUID id);
}
