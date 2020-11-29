package org.bataykin.service.client;

import org.bataykin.BaseTest;
import org.bataykin.dto.common.PageWrapper;
import org.bataykin.model.client.Client;
import org.bataykin.model.client.FullName;
import org.bataykin.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static java.util.Collections.singletonList;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.bataykin.model.enums.CardType.GOLD;
import static org.bataykin.model.enums.Currency.BYN;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.PageRequest.of;

public class ClientServiceImplTest extends BaseTest {

    @Autowired
    private ClientService clientService;

    private Client testClient;

    @BeforeEach
    public void setUp(@Autowired final ClientRepository clientRepository) {
        final FullName fullName = new FullName();

        fullName.setFirstName("Bob");
        fullName.setLastName("Nelson");
        fullName.setMiddleName("Olegovich");

        testClient = new Client();
        testClient.setId(1L);
        testClient.setCardId(1L);
        testClient.setFullName(fullName);
        testClient.setPhoneNumber("+375001112233");
        testClient.setEmail("fooEmail@boo.com");
        testClient.setStatus(1);

        final Pageable pageable = of(1, 1);
        final Page<Client> clientPage = new PageImpl(singletonList(testClient), pageable, 1);

        when(clientRepository.save(testClient))
                .thenReturn(testClient);

        when(clientRepository.findById(1L))
                .thenReturn(ofNullable(testClient));

        when(clientRepository.findAllClientsByCurrencyAndCardType(BYN, GOLD, pageable))
                .thenReturn(clientPage);
    }

    @Test
    @DisplayName("Client create test")
    public void createTest() {
        final String firstName = "Bob";
        final String email = "fooEmail@boo.com";
        final Client client = clientService.create(testClient);

        assertThat(client.getFullName().getFirstName())
                .startsWith("Bo")
                .endsWith("b")
                .isEqualToIgnoringCase(firstName);

        assertThat(client.getPhoneNumber())
                .isEqualTo(testClient.getPhoneNumber());

        assertThat(client.getEmail())
                .isEqualTo(email);

        assertThat(client)
                .isEqualTo(testClient);
    }

    @Test
    @DisplayName("Get client by id test")
    public void getClientTest() {
        final Long id = 1L;
        final String firstName = "Bob";
        final Client client = clientService.getClient(id);

        assertThat(client.getCardId())
                .isEqualTo(id);

        assertThat(client.getFullName().getFirstName())
                .isEqualTo(firstName);
    }

    @Test
    @DisplayName("Get client phones by currency and card type test")
    public void getPhonesTest() {
        final String phoneNumber = "+375001112233";
        final PageWrapper<String> phones = clientService.getPhones(BYN, GOLD, of(1, 1));

        assertThat(phones.getElements().get(0))
                .isEqualTo(phoneNumber);

        assertThat(phones.getElements().size())
                .isEqualTo(1);
    }
}
