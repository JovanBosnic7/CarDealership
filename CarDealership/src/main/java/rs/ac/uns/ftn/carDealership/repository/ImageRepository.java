package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.ac.uns.ftn.carDealership.model.vehicle.ImageModel;

import java.util.Optional;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<ImageModel, UUID> {
    Optional<ImageModel> findByName(String name);

    @Query("select image from ImageModel image where image.name like ?1")
    ImageModel findWithoutType(String name);
}
