package org.bataykin.service.client;

import org.bataykin.dto.common.PageWrapper;
import org.bataykin.model.client.Client;
import org.bataykin.model.enums.CardType;
import org.bataykin.model.enums.Currency;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Client create(final Client client);

    Client getClient(final Long id);

    PageWrapper<String> getPhones(final Currency currency, final CardType cardType, final Pageable pageable);
}
