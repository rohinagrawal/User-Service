package dev.rohin.userservice.event;

import dev.rohin.userservice.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class SuccessfulRegistrationEvent extends ApplicationEvent {

    private final User registeredUser;

    public SuccessfulRegistrationEvent(User registeredUser) {
        super(registeredUser);
        this.registeredUser = registeredUser;
    }
}
