package cl.bibliotecaam.empleado.empleado.auth;

import cl.bibliotecaam.empleado.empleado.user.Role;
import cl.bibliotecaam.empleado.empleado.user.User;
import cl.bibliotecaam.empleado.empleado.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    public AuthResponse login(LoginRequest request){
        return null;
    }

    public AuthResponse register(RegisterRequest request){
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .country(request.getCountry())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
