package uz.nevermore.jwt_securiy.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class RegisterRequest {

    private String username;

    private String password;
}
