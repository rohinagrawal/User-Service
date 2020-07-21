package dev.rohin.userservice.service;

import dev.rohin.userservice.dto.UserDto;
import dev.rohin.userservice.event.SuccessfulRegistrationEvent;
import dev.rohin.userservice.model.User;
import dev.rohin.userservice.model.VerificationToken;
import dev.rohin.userservice.repository.UserRepository;
import dev.rohin.userservice.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

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

        applicationEventPublisher.publishEvent(new SuccessfulRegistrationEvent(savedUser));

        return savedUser;
    }

    @Override
    public User validateUser(String token) {

        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);

        if (verificationToken.isEmpty()){
            return null;
        }
        if(verificationToken.get().getExpiryTime().getTime() - new Date().getTime() > 0 ){
            User verifiedUser = verificationToken.get().getUser();
            verifiedUser.setActive(true);

            userRepository.save(verifiedUser);

            verificationTokenRepository.delete(verificationToken.get());

            return verifiedUser;
        }
        else{
            return null;
        }
    }
}
