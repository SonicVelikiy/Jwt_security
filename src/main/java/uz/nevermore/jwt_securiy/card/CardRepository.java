package uz.nevermore.jwt_securiy.card;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository  extends JpaRepository<Card,Long> {
    List<Card>findAllByUserId(Long userId);
    Optional<Card>findByUser_PasswordAndUserUsername(String user_password, String user_username);
    Optional<Card>findByUser_Username(String username);

    Optional<Card>findByNumber(String number);

    Optional<Card>findByUserId(Long user_id);


}
