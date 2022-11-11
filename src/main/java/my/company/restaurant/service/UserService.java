package my.company.restaurant.service;

import my.company.restaurant.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(UserDTO userDTO);
}
