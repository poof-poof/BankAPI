package org.bataykin.model.client;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bataykin.model.BaseEntity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "client")
@EqualsAndHashCode(callSuper = true)
public class Client extends BaseEntity {

    @Embedded
    private FullName fullName;

    private String phoneNumber;

    private String email;

    private Integer status;

    private Long cardId;
}
