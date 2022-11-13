package my.company.restaurant.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import my.company.restaurant.dto.UserDTO;
import my.company.restaurant.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Registration new user")
    @PostMapping("/register")
    public void register(@RequestBody UserDTO userDTO) {
        userService.register(userDTO);
    }
}
