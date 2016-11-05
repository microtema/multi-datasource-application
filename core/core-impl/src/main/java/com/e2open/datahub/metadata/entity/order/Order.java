package com.e2open.datahub.metadata.entity.order;

import lombok.*;
import lombok.experimental.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "id")
@Table(name = "ORDERS ")
public class Order {


    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
}
