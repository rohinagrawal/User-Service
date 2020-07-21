package dev.rohin.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordResponseDto {

    private String email;

    private String fullName;

    public ResetPasswordResponseDto(String email, String fullName) {
        this.email = email;
        this.fullName = fullName;
    }
}
