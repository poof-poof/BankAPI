package org.bataykin.transformer;

import org.bataykin.dto.Dto;

import java.io.Serializable;

public interface ToDtoTransformer<E extends Serializable, D extends Dto> {

    D transform(E entity);
}
