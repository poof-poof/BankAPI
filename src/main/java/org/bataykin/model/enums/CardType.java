package org.bataykin.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CardType {

    CLASSIC(0),
    GOLD(1),
    PLATINUM(2);

    private final int status;
}
