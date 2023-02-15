package uz.nevermore.jwt_securiy.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.nevermore.jwt_securiy.config.JwtService;
import uz.nevermore.jwt_securiy.user.User;
import uz.nevermore.jwt_securiy.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;



    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user= User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword())).build();
                userRepository.save(user);
                var jwtToken= jwtService.generateToken(user);
                return AuthenticationResponse.builder().token(jwtToken).build();

    }

    public AuthenticationResponse authenticate(RegisterRequest registerRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getUsername(),registerRequest.getPassword()));
        var user= userRepository.findByUsername(registerRequest.getUsername()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
}
