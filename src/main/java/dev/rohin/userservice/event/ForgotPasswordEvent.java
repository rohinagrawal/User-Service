package dev.rohin.userservice.event;

import dev.rohin.userservice.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class ForgotPasswordEvent extends ApplicationEvent {

    private User forgotPasswordUser;

    public ForgotPasswordEvent( User forgotPasswordUser) {
        super(forgotPasswordUser);
        this.forgotPasswordUser = forgotPasswordUser;
    }
}
