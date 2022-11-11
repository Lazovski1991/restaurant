package my.company.restaurant.service;

import lombok.RequiredArgsConstructor;
import my.company.restaurant.dto.UserDTO;
import my.company.restaurant.exception.AuthException;
import my.company.restaurant.model.User;
import my.company.restaurant.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void register(UserDTO userDTO) {
        User user = new User()
                .setRole(userDTO.getRole())
                .setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()))
                .setName(userDTO.getName())
                .setEmail(userDTO.getEmail())
                .setDataRegistrationUser(LocalDateTime.now());

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException("Error receive user"));
    }
}
