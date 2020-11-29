package org.bataykin.config;

import org.bataykin.repository.CardRepository;
import org.bataykin.repository.ClientRepository;
import org.bataykin.service.card.CardService;
import org.bataykin.service.card.CardServiceImpl;
import org.bataykin.service.client.ClientService;
import org.bataykin.service.client.ClientServiceImpl;
import org.bataykin.transformer.card.CardTransformer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ApplicationConfigTest {

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private CardRepository cardRepository;

    @MockBean
    private CardTransformer cardTransformer;

    @Bean
    public ClientService clientService() {
        return new ClientServiceImpl(clientRepository);
    }

    @Bean
    public CardService cardService() {
        return new CardServiceImpl(cardRepository, clientService(), cardTransformer);
    }
}
