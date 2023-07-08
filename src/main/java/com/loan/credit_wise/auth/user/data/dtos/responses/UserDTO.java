package com.loan.credit_wise.auth.user.data.dtos.responses;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.loan.credit_wise.auth.user.data.models.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    @JsonUnwrapped
    private String Address;
    private Set<Role> role;
    private LocalDateTime registeredAt;
    private boolean enabled;

}
