package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.carDealership.TestDriveStatus;

import java.util.UUID;

public interface TestDriveStatusRepository extends JpaRepository<TestDriveStatus, UUID> {
    TestDriveStatus findTestDriveStatusByTestDrive_TestDriveId(UUID testDriveId);
}
