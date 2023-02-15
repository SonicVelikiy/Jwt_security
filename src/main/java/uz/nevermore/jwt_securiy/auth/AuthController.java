package uz.nevermore.jwt_securiy.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Handler;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService service;
    @PostMapping("/register")
    public HttpEntity<?>register(@RequestBody RegisterRequest registerRequest){
    return ResponseEntity.ok(service.register(registerRequest));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>authenticate(@RequestBody RegisterRequest registerRequest ){
        return ResponseEntity.ok(service.authenticate(registerRequest));
    }

}
