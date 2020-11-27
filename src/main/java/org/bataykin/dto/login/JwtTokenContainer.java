package org.bataykin.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bataykin.dto.Dto;

@Data
@AllArgsConstructor
public class JwtTokenContainer implements Dto {

    private String accessToken;
}
