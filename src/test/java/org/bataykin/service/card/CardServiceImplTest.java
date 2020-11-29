package org.bataykin.service.card;

import org.bataykin.BaseTest;
import org.bataykin.dto.CardContainerDto;
import org.bataykin.dto.card.CardDto;
import org.bataykin.model.card.Card;
import org.bataykin.model.client.Client;
import org.bataykin.model.client.FullName;
import org.bataykin.repository.CardRepository;
import org.bataykin.repository.ClientRepository;
import org.bataykin.service.client.ClientService;
import org.bataykin.transformer.card.CardTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bataykin.model.enums.CardType.GOLD;
import static org.bataykin.model.enums.Currency.BYN;
import static org.mockito.Mockito.when;

public class CardServiceImplTest extends BaseTest {

    @Autowired
    private CardService cardService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardTransformer cardTransformer;

    @BeforeEach
    public void setUp() {
        final FullName fullName = new FullName();

        fullName.setFirstName("Bob");
        fullName.setLastName("Nelson");
        fullName.setMiddleName("Olegovich");

        final Client client = new Client();
        client.setId(1L);
        client.setCardId(1L);
        client.setFullName(fullName);
        client.setPhoneNumber("+375001112233");
        client.setEmail("fooEmail@boo.com");
        client.setStatus(1);

        final CardDto cardDto = new CardDto();
        cardDto.setId(1L);
        cardDto.setNumber("123412341234");
        cardDto.setCurrency("BYN");
        cardDto.setType("GOLD");

        final Card card = new Card();
        card.setId(1L);
        card.setNumber("123412341234");
        card.setCurrency(BYN);
        card.setType(GOLD);

        when(cardTransformer.transform(cardDto))
                .thenReturn(card);

        when(cardRepository.save(card))
                .thenReturn(card);

        when(clientRepository.findById(1L))
                .thenReturn(of(client));

    }

    @Test
    @DisplayName("Create and link card with client test")
    public void createCardForClientTest() {
        final Long clientId = 1L;
        final Long cardId = 1L;
        final String cardNumber = "123412341234";
        final String currencyDto = "BYN";
        final String cardTypeDto = "GOLD";
        final CardDto cardDto = new CardDto();
        final CardContainerDto cardContainerDto = new CardContainerDto();

        cardDto.setId(cardId);
        cardDto.setNumber(cardNumber);
        cardDto.setCurrency(currencyDto);
        cardDto.setType(cardTypeDto);

        cardContainerDto.setCardDto(cardDto);
        cardContainerDto.setClientId(clientId);

        cardService.createCardForClient(cardContainerDto);

        Client updateClient = clientService.getClient(clientId);

        assertThat(updateClient.getCardId())
                .isEqualTo(cardId);

        assertThat(updateClient.getStatus())
                .isEqualTo(GOLD.getStatus());
    }
}
