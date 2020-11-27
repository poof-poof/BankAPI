package org.bataykin.service.jwt.generate;

import org.bataykin.dto.login.JwtTokenContainer;

public interface JwtGenerateStrategy<T> {

    JwtTokenContainer generate(T param);
}
