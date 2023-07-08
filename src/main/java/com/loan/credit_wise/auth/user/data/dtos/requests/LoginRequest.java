package com.loan.credit_wise.auth.user.data.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static com.loan.credit_wise.credit_wise_utils.AppUtils.NOT_BLANK;
import static com.loan.credit_wise.credit_wise_utils.AppUtils.NOT_NULL;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    @NotNull(message = NOT_NULL)
    @NotBlank(message = NOT_BLANK)
    private String email;

    @NotNull(message = NOT_NULL)
    @NotBlank(message = NOT_BLANK)
    private String password;
}
