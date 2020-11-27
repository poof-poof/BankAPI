package org.bataykin.dto.login;

import lombok.Data;
import org.bataykin.dto.Dto;

@Data
public class LoginRequest implements Dto {

    private String username;
    private String password;
}
