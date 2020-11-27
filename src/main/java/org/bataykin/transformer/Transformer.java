package org.bataykin.transformer;

import org.bataykin.dto.Dto;

import java.io.Serializable;

public interface Transformer<E extends Serializable, D extends Dto> extends ToDtoTransformer<E, D>, ToEntityTransformer<E, D> {
}
