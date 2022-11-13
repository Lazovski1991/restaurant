package my.company.restaurant.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;
import my.company.restaurant.model.Role;

@Data
@Accessors(chain = true)
public class UserDTO {
    @Schema(description = "User name")
    private String name;
    @Schema(description = "User email")
    private String email;
    @Schema(description = "Password")
    private String password;
    @Schema(description = "Role (ADMIN or USER)")
    private Role role;
}
