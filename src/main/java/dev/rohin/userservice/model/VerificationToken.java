package dev.rohin.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = User.class)
    private User user;

    private Date expiryTime;

    private String token;

    private static final int VALIDITY_TIME = 4 * 60;

    public VerificationToken(){}

    public VerificationToken(User user){

        this.token=generateRandomUniqueToken();
        this.user=user;
        this.expiryTime=calculateExpiryTime();
    }

    private Date calculateExpiryTime() {
        Calendar calendar = Calendar.getInstance();

        Date currentTimeAndDate = new Date();

        calendar.setTimeInMillis(currentTimeAndDate.getTime());
        calendar.add(Calendar.MINUTE,VALIDITY_TIME);

        return new Date(calendar.getTime().getTime());
    }

    private String generateRandomUniqueToken() {
        return UUID.randomUUID().toString();
    }

    public void updateToken(){
        this.token=generateRandomUniqueToken();
        this.expiryTime=calculateExpiryTime();
    }
}
