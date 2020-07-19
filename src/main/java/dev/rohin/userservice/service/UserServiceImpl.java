package dev.rohin.userservice.service;

import dev.rohin.userservice.dto.UserDto;
import dev.rohin.userservice.model.User;
import dev.rohin.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public User registerUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()) != null){
            // TODO: Throw Exception
        }

        User user = new User();

        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setActive(false);
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); //TODO: Encrypt the Password

        User savedUser = userRepository.save(user); //savedUser will have an Id as well whereas the original user object wont.
        return savedUser;
    }
}
