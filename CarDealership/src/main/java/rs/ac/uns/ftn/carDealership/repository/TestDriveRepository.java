package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.carDealership.model.carDealership.TestDrive;

import java.util.ArrayList;
import java.util.UUID;

public interface TestDriveRepository extends JpaRepository<TestDrive, UUID> {
    @Query("select td from TestDrive td where td.client.userId = ?1")
    ArrayList<TestDrive> findTestDriveByClient(UUID clientId);
}
