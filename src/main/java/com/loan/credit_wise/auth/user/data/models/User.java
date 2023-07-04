package com.loan.credit_wise.auth.user.data.models;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.loan.credit_wise.auth.user.data.models.Address;
import com.loan.credit_wise.auth.user.data.models.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;

    private String lastName;

    @Column(unique = true,  nullable = false)
    private String email;

    private String password;

    @JsonUnwrapped
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    private String userImage;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    private final LocalDateTime registeredAt = LocalDateTime.now();

    private boolean enabled;

}
