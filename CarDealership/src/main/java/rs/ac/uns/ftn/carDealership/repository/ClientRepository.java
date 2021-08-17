package rs.ac.uns.ftn.carDealership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.uns.ftn.carDealership.model.users.Client;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
