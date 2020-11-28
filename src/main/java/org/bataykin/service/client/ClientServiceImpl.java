package org.bataykin.service.client;

import lombok.RequiredArgsConstructor;
import org.bataykin.dto.common.PageWrapper;
import org.bataykin.exception.EntityNotFoundException;
import org.bataykin.model.client.Client;
import org.bataykin.model.enums.CardType;
import org.bataykin.model.enums.Currency;
import org.bataykin.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public Client create(final Client client) {
        return clientRepository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Client getClient(final Long id) {
        return clientRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public PageWrapper<String> getPhones(Currency currency, CardType cardType, Pageable pageable) {
        return getPhonesDtos(clientRepository.findAllClientsByCurrencyAndCardType(currency, cardType, pageable));
    }

    private PageWrapper<String> getPhonesDtos(final Page<Client> page) {
        return PageWrapper.<String>builder()
                .elements(page.getContent().stream().map(Client::getPhoneNumber).collect(toList()))
                .totalElements(page.getTotalElements())
                .build();
    }
}
