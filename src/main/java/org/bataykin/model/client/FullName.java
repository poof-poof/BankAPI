package org.bataykin.model.client;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class FullName implements Serializable {

    private String firstName;

    private String lastName;

    private String middleName;
}
