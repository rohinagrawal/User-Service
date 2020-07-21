package dev.rohin.userservice.service;

import dev.rohin.userservice.dto.UserDto;
import dev.rohin.userservice.model.User;

public interface UserService {

    User registerUser(UserDto userDto);

    User validateUser(String token);
}
