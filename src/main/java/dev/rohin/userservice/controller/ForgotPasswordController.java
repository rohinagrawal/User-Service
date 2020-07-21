package dev.rohin.userservice.controller;

import dev.rohin.userservice.dto.*;
import dev.rohin.userservice.model.ResetPasswordToken;
import dev.rohin.userservice.model.User;
import dev.rohin.userservice.repository.ResetPasswordTokenRepository;
import dev.rohin.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ForgotPasswordController {

    @Autowired
    UserService userService;

    @PostMapping("/user/forgotpassword/")
    public ResponseDto<ForgotPasswordResponseDto> forgotPassword (@RequestBody ForgotPasswordDto forgotPasswordDto){

        User forgotPasswordUser = userService.forgotPassword(forgotPasswordDto);

        return new ResponseDto<>(
                HttpStatus.OK,
                new ForgotPasswordResponseDto(
                        forgotPasswordUser.getEmail()
                )
        );
    }

    @PostMapping("/user/resetpassword/")
    public ResponseDto<ResetPasswordResponseDto> resetPassword (@RequestParam String token, @RequestBody ResetPasswordDto resetPasswordDto){

        User resetPasswordUser = userService.resetPassword(token,resetPasswordDto);

        return new ResponseDto<>(
                HttpStatus.OK,
                new ResetPasswordResponseDto(
                        resetPasswordUser.getEmail(),
                        resetPasswordUser.getFullName()
                )
        );
    }
}
