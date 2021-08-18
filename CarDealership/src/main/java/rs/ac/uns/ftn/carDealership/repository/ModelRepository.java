package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.vehicle.Model;

import java.util.List;
import java.util.UUID;

public interface ModelRepository extends JpaRepository<Model, UUID> {
    List<Model> getModelsByMark_MarkId(UUID markId);
}
