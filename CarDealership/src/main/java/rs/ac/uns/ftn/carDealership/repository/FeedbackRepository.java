package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.carDealership.Feedback;

import java.util.ArrayList;
import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {

    ArrayList<Feedback> findAllByVehicle_VehicleId(UUID vehicleId);
}
