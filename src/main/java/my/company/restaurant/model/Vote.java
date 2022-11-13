package my.company.restaurant.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue
    @Column(name = "vote_id", updatable = false, nullable = false, columnDefinition="UUID")
    private UUID id;
    @Column(name = "restaurant_id")
    private String restaurantId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "vote_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime voteDateTime;
}
