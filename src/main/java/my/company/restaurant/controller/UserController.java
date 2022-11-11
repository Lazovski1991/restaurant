package my.company.restaurant.controller;

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
    @PostMapping("/register")
    public void register(@RequestBody UserDTO userDTO) {
        userService.register(userDTO);
    }
}
