package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.vehicle.Mark;

import java.util.UUID;

public interface MarkRepository extends JpaRepository<Mark, UUID> {
}
