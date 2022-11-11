package my.company.restaurant.service;

import my.company.restaurant.dto.AuthenticationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    String login(AuthenticationDTO request);


    void logOut(HttpServletRequest request, HttpServletResponse response);
}
