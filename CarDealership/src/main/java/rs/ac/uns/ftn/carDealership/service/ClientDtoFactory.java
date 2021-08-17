package rs.ac.uns.ftn.carDealership.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.carDealership.model.auth.Role;
import rs.ac.uns.ftn.carDealership.model.dto.CreateClient;
import rs.ac.uns.ftn.carDealership.model.users.Client;
import rs.ac.uns.ftn.carDealership.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ClientDtoFactory {

    @Autowired
    RoleRepository roleRepository;

    public Client buildClientFromDto(CreateClient dto) {
        Client client = new Client();
        client.setUserId(UUID.randomUUID());
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findRoleByRole("client"));
        client.setRoles(roles);
        client.setUsername(dto.getUsername());
        client.setName(dto.getName());
        client.setLastName(dto.getLastName());
        client.setActive(true);
        client.setPassword(dto.getPassword());
        return client;
    }
}
