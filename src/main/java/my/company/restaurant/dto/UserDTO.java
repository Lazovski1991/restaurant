package my.company.restaurant.dto;


import lombok.Data;
import lombok.experimental.Accessors;
import my.company.restaurant.model.Role;

@Data
@Accessors(chain = true)
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
}
