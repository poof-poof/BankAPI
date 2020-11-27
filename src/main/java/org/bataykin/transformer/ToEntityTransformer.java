package org.bataykin.transformer;

import org.bataykin.dto.Dto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.io.Serializable;
import java.util.List;

import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;

public interface ToEntityTransformer<E extends Serializable, D extends Dto> {

    E transform(D dto);

    @IterableMapping(nullValueMappingStrategy = RETURN_DEFAULT)
    List<E> transform(List<D> dtos);

    @Mapping(target = "id", ignore = true)
    void update(D dto, @MappingTarget E targetEntity);
}
