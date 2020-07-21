package dev.rohin.userservice.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class ResetPasswordDto {

    @NotEmpty
    @Size(min = 3)
    private String password;
}
