package org.bataykin.service.client;

import lombok.RequiredArgsConstructor;
import org.bataykin.exception.EntityNotFoundException;
import org.bataykin.model.client.Client;
import org.bataykin.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public Client create(final Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClient(final Long id) {
        return clientRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
