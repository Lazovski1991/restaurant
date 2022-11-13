package my.company.restaurant.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class VoteDto {
    private UUID id;
    private String restaurantId;
    private String userId;
    private LocalDateTime voteDateTime;
}
