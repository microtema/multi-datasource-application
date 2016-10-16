package com.e2open.datahub.staging.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
public class StagingOrder {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;
}
