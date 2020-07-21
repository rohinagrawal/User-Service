package dev.rohin.userservice.event.listener;

import dev.rohin.userservice.event.SuccessfulRegistrationEvent;
import dev.rohin.userservice.model.User;

import dev.rohin.userservice.model.VerificationToken;
import dev.rohin.userservice.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SuccessfulRegistrationEventListener implements ApplicationListener<SuccessfulRegistrationEvent>{

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Override
    public void onApplicationEvent(SuccessfulRegistrationEvent successfulRegistrationEvent) {

        User registeredUser = successfulRegistrationEvent.getRegisteredUser();

        VerificationToken token = new VerificationToken(registeredUser);

        verificationTokenRepository.save(token);
    }
}
