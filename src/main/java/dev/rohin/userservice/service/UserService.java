package dev.rohin.userservice.service;

import dev.rohin.userservice.dto.ForgotPasswordDto;
import dev.rohin.userservice.dto.ResetPasswordDto;
import dev.rohin.userservice.dto.UserDto;
import dev.rohin.userservice.model.User;

public interface UserService {

    User registerUser(UserDto userDto);

    User validateUser(String token);

    User forgotPassword(ForgotPasswordDto forgotPasswordDto);

    User resetPassword(String token, ResetPasswordDto resetPasswordDto);
}
