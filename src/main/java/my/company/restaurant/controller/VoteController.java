package my.company.restaurant.controller;

import lombok.RequiredArgsConstructor;
import my.company.restaurant.dto.RestaurantDto;
import my.company.restaurant.service.VoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping("/vote/{idRestaurant}")
    public void addVote(@PathVariable UUID idRestaurant) {

    }

    @DeleteMapping("/vote/{idRestaurant}")
    public void deleteVote(@PathVariable UUID idRestaurant) {

    }

    @GetMapping("/{idMenu}")
    public List<RestaurantDto> listMenu() {
        return null;
    }
}
