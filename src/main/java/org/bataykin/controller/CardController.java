package org.bataykin.controller;

import lombok.RequiredArgsConstructor;
import org.bataykin.dto.CardContainerDto;
import org.bataykin.service.card.CardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/card")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @ResponseStatus(CREATED)
    @PostMapping
    void create(@RequestBody final CardContainerDto containerDto) {
        cardService.createCardForClient(containerDto);
    }
}
