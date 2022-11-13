package my.company.restaurant.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class RestaurantDTO {
    private UUID restaurantId;
    private String restaurantName;
    private String dishName;
    private BigDecimal price;
}
