package org.bataykin.dto.client;

import lombok.Data;
import org.bataykin.dto.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
public class ClientDto implements Dto {

    @Null
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String middleName;

    @NotBlank
    private String phoneNumber;

    @Email
    private String email;

    @Null
    private Integer status;
}
