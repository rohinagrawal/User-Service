package dev.rohin.userservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ResetPasswordToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    private Date expiryTime;

    @OneToOne(targetEntity = User.class)
    private User user;

    private static final int VALIDITY_TIME = 1 * 60; // 1 hr in minutes

    private Date calculateExpiryTime(){

        Date currentTimeAndDate = new Date();

        Calendar calendar = Calendar.getInstance();

        calendar.setTimeInMillis(currentTimeAndDate.getTime());
        calendar.add(Calendar.MINUTE,VALIDITY_TIME);

        return calendar.getTime();
    }

    private String generateToken()
    {
        return UUID.randomUUID().toString();
    }

    public ResetPasswordToken() {}

    public ResetPasswordToken(User user) {
        this.token = generateToken();
        this.expiryTime = calculateExpiryTime();
        this.user = user;
    }
}
