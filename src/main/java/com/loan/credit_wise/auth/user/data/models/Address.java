package com.loan.credit_wise.auth.user.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    private String houseNumber;

    private String streetName;

    private String city;

    private String state;

    private String zipCode;
}
