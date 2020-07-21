package dev.rohin.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ForgotPasswordDto {

    @NotEmpty
    private String email;
}
