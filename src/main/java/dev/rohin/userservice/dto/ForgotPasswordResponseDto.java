package dev.rohin.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordResponseDto {

    private String email;

    public ForgotPasswordResponseDto(String email) {
        this.email = email;
    }
}
