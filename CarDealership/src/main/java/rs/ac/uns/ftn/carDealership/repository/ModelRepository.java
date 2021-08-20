package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.carDealership.model.vehicle.Model;

import java.util.List;
import java.util.UUID;

public interface ModelRepository extends JpaRepository<Model, UUID> {
    List<Model> getModelsByMark_MarkId(UUID markId);
    @Query("select model from Model model where model.modelId = ?1")
    Model getModelById(UUID modelId);
}
