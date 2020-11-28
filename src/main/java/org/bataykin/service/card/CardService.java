package org.bataykin.service.card;

import org.bataykin.dto.CardContainerDto;

public interface CardService {
    void createCardForClient(final CardContainerDto containerDto);
}
