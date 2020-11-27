package org.bataykin.service.client;

import org.bataykin.model.client.Client;

public interface ClientService {

    Client create(final Client client);

    Client getClient(final Long id);
}
