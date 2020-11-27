package org.bataykin.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bataykin.dto.AuthUserDto;
import org.bataykin.dto.Dto;

@Data
@AllArgsConstructor
public class LoginResponse implements Dto {

    private JwtTokenContainer tokens;
    private AuthUserDto authUser;
}
