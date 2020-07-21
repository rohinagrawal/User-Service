package dev.rohin.userservice.event.listener;

import dev.rohin.userservice.event.ForgotPasswordEvent;
import dev.rohin.userservice.model.ResetPasswordToken;
import dev.rohin.userservice.model.User;
import dev.rohin.userservice.repository.ResetPasswordTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ForgotPasswordEventListener implements ApplicationListener<ForgotPasswordEvent> {

    @Autowired
    ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Override
    public void onApplicationEvent(ForgotPasswordEvent forgotPasswordEvent) {

        User forgotPasswordUser = forgotPasswordEvent.getForgotPasswordUser();

        ResetPasswordToken resetPasswordToken = new ResetPasswordToken(forgotPasswordUser);

        resetPasswordTokenRepository.save(resetPasswordToken);
    }
}
