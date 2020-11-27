package org.bataykin.dto.common;

import lombok.Builder;
import lombok.Data;
import org.bataykin.dto.Dto;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public final class PageWrapper<T extends Serializable> implements Dto {

    private Long totalElements;
    private List<T> elements;
}
