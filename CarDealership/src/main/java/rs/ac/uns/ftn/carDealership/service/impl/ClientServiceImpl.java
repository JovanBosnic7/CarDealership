package rs.ac.uns.ftn.carDealership.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.carDealership.model.users.Client;
import rs.ac.uns.ftn.carDealership.repository.ClientRepository;
import rs.ac.uns.ftn.carDealership.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
