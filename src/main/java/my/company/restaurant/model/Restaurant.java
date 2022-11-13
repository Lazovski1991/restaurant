package my.company.restaurant.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "restaurant")
@Data
@Accessors(chain = true)
public class Restaurant {
    @Id
    @GeneratedValue
    @Column(name = "restaurant_id", updatable = false, nullable = false, columnDefinition="UUID")
    private UUID id;
    @Column(name = "restaurant_name")
    private String restaurantName;
    @Column(name = "dish_name")
    private String dishName;
    @Column(name = "dish_price")
    private BigDecimal dishPrice;
    @Column(name = "dish_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dishDate;
}
