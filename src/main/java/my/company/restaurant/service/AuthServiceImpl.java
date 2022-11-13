package my.company.restaurant.service;

import lombok.RequiredArgsConstructor;
import my.company.restaurant.dto.AuthenticationDTO;
import my.company.restaurant.exception.AuthException;
import my.company.restaurant.model.User;
import my.company.restaurant.repository.UserRepository;
import my.company.restaurant.secutiry.JwtBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtBuilder jwtBuilder;

    @Override
    public String login(AuthenticationDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new AuthException("User doesn't exist"));
            return jwtBuilder.createTokens(request.getEmail(), user.getRole().name(), user.getId());
        } catch (AuthenticationException e) {
            throw new AuthException("Error authentication");
        }
    }

    @Override
    public void logOut(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, response, null);
    }
}
