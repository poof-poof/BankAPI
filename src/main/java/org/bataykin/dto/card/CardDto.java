package org.bataykin.dto.card;

import lombok.Data;
import org.bataykin.dto.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
public class CardDto implements Dto {

    @Null
    private Long id;

    @NotBlank
    private String number;

    @NotBlank
    private String currency;

    @NotBlank
    private String type;
}
