package org.bataykin.service.card;

import lombok.RequiredArgsConstructor;
import org.bataykin.dto.CardContainerDto;
import org.bataykin.model.card.Card;
import org.bataykin.model.client.Client;
import org.bataykin.repository.CardRepository;
import org.bataykin.service.client.ClientService;
import org.bataykin.transformer.card.CardTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final ClientService clientService;
    private final CardTransformer transformer;

    @Override
    @Transactional
    public void createCardForClient(CardContainerDto containerDto) {
        final Client client = clientService.getClient(containerDto.getClientId());
        final Card card = cardRepository.save(transformer.transform(containerDto.getCardDto()));

        client.setStatus(card.getType().getStatus());
        client.setCardId(card.getId());
    }
}
