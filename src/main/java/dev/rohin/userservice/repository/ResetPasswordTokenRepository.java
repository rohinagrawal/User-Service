package dev.rohin.userservice.repository;

import dev.rohin.userservice.model.ResetPasswordToken;
import dev.rohin.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetPasswordTokenRepository extends JpaRepository <ResetPasswordToken, Long>{

    Optional<ResetPasswordToken> findByToken(String token);

}
