package my.company.restaurant.controller;

import lombok.RequiredArgsConstructor;
import my.company.restaurant.dto.AuthenticationDTO;
import my.company.restaurant.dto.UserDTO;
import my.company.restaurant.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    public final AuthService authService;

    @PostMapping("/login")
    public String authenticate(@RequestBody AuthenticationDTO request) {
        return authService.login(request);
    }

    @PostMapping("/logout")
    public void logOut(HttpServletRequest request, HttpServletResponse response) {
        authService.logOut(request, response);
    }
}
