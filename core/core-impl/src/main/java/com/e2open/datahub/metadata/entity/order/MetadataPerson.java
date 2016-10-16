package com.e2open.datahub.metadata.entity.order;

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
public class MetadataPerson {


    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

}
