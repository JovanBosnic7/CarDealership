package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User getByUsername(String username);
}
