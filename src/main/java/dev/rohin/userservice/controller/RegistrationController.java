package dev.rohin.userservice.controller;

import dev.rohin.userservice.dto.ResponseDto;
import dev.rohin.userservice.dto.UserDto;
import dev.rohin.userservice.dto.UserResponseDto;
import dev.rohin.userservice.model.User;
import dev.rohin.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class  RegistrationController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseDto<UserResponseDto> registerUser(@RequestBody UserDto userDto) {

        User registeredUser= userService.registerUser(userDto);

        return new ResponseDto<>(
                HttpStatus.OK,
                new UserResponseDto(
                        registeredUser.getId(),
                        registeredUser.getFullName(),
                        registeredUser.getEmail(),
                        registeredUser.isActive()
                )
        );

    }

    @GetMapping("/user/confirm")
    public ResponseDto<UserResponseDto> validateUser(@RequestParam String token){
        User verifiedUser = userService.validateUser(token);

        return new ResponseDto<>(
                HttpStatus.OK,
                new UserResponseDto(
                        verifiedUser.getId(),
                        verifiedUser.getFullName(),
                        verifiedUser.getEmail(),
                        verifiedUser.isActive()
                )
        );
    }
}
