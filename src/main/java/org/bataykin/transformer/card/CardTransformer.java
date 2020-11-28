package org.bataykin.transformer.card;

import org.bataykin.dto.card.CardDto;
import org.bataykin.model.card.Card;
import org.bataykin.transformer.Transformer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardTransformer extends Transformer<Card, CardDto> {}
