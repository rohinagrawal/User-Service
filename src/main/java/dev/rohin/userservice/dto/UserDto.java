package dev.rohin.userservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {

    @NotEmpty
    @Size(min=3)
    private String fullName;

    @NotEmpty
    @Size(min=4)
    private String email;
    // TODO : Implement custom Validators

    @NotEmpty
    @Size(min=6)
    private String password;
}
