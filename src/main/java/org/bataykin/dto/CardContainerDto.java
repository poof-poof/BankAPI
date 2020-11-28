package org.bataykin.dto;

import lombok.Data;
import org.bataykin.dto.card.CardDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class CardContainerDto implements Dto {

    @Valid
    private CardDto cardDto;

    @NotNull
    private Long clientId;
}
