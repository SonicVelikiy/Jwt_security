package uz.nevermore.jwt_securiy.card;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.nevermore.jwt_securiy.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EditCardRequest {

    private Long userId;

    private String number;

    private double balance;

    private String expireDate;

    private boolean active=false;
}
