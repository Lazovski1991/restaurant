package my.company.restaurant.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthenticationDTO {
    private String email;
    private String password;
}
