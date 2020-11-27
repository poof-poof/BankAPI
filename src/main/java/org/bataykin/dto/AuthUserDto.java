package org.bataykin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AuthUserDto implements Dto {

    private Long id;
    private String username;
    private List<String> roles;
}
