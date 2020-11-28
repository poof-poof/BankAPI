package org.bataykin.model.card;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bataykin.model.BaseEntity;
import org.bataykin.model.enums.CardType;
import org.bataykin.model.enums.Currency;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import static javax.persistence.EnumType.ORDINAL;
import static javax.persistence.EnumType.STRING;

@Data
@Entity
@Table(name = "card")
@EqualsAndHashCode(callSuper = true)
public class Card extends BaseEntity {

    private String number;

    @Enumerated(STRING)
    private Currency currency;

    @Enumerated(ORDINAL)
    private CardType type;
}
