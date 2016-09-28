package com.e2open.datahub.staging.entity;

import lombok.*;
import lombok.experimental.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class StagingPerson {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;
}
