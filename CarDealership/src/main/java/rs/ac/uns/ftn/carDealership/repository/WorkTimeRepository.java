package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.carDealership.WorkTime;

import java.util.UUID;

public interface WorkTimeRepository extends JpaRepository<WorkTime, UUID> {
}
