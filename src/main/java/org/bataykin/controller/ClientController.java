package org.bataykin.controller;

import lombok.RequiredArgsConstructor;
import org.bataykin.dto.client.ClientDto;
import org.bataykin.model.client.Client;
import org.bataykin.service.client.ClientService;
import org.bataykin.transformer.client.ClientTransformer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientTransformer transformer;
    private final ClientService clientService;

    @ResponseStatus(value = CREATED)
    @PostMapping
    Client create(@Valid @RequestBody final ClientDto clientDto) {
        return clientService.create(transformer.transform(clientDto));
    }

    @GetMapping(value = "/{id}")
    ClientDto getClient(@PathVariable final Long id) {
        return transformer.transform(clientService.getClient(id));
    }
}
