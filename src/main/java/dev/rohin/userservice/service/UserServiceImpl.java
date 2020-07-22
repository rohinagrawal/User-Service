package dev.rohin.userservice.service;

import dev.rohin.userservice.dto.ForgotPasswordDto;
import dev.rohin.userservice.dto.ResetPasswordDto;
import dev.rohin.userservice.dto.UserDto;
import dev.rohin.userservice.event.ForgotPasswordEvent;
import dev.rohin.userservice.event.SuccessfulRegistrationEvent;
import dev.rohin.userservice.model.ResetPasswordToken;
import dev.rohin.userservice.model.User;
import dev.rohin.userservice.model.VerificationToken;
import dev.rohin.userservice.repository.ResetPasswordTokenRepository;
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

    @Autowired
    ResetPasswordTokenRepository resetPasswordTokenRepository;

    //@Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public User registerUser(UserDto userDto) {
        if (userRepository.findAllByEmail(userDto.getEmail()) != null){
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

    @Override
    public User forgotPassword(ForgotPasswordDto forgotPasswordDto) {

        User forgotPasswordUser = userRepository.findByEmail(forgotPasswordDto.getEmail());

        forgotPasswordUser.setActive(false);

        userRepository.save(forgotPasswordUser);

        applicationEventPublisher.publishEvent(new ForgotPasswordEvent(forgotPasswordUser));

        return forgotPasswordUser;
    }

    @Override
    public User resetPassword(String token, ResetPasswordDto resetPasswordDto) {

        Optional<ResetPasswordToken> resetPasswordToken = resetPasswordTokenRepository.findByToken(token);

        if(resetPasswordToken.isEmpty())
            return null;
        if(resetPasswordToken.get().getExpiryTime().getTime() - new Date().getTime() > 0 ){

            User resetPasswordUser = resetPasswordToken.get().getUser();

            resetPasswordUser.setPassword(passwordEncoder.encode(resetPasswordDto.getPassword()));

            resetPasswordUser.setActive(true);

            userRepository.save(resetPasswordUser);

            resetPasswordTokenRepository.delete(resetPasswordToken.get());

            return resetPasswordUser;
        }
        else{
            return null;
        }
    }
}
