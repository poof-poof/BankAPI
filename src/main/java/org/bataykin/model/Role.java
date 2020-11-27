package org.bataykin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bataykin.model.enums.ERole;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import static javax.persistence.EnumType.STRING;

@Data
@Entity
@Table(name = "role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    @Enumerated(STRING)
    private ERole name;
}
